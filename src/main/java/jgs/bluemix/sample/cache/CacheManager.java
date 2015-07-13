package jgs.bluemix.sample.cache;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.websphere.objectgrid.ClientClusterContext;
import com.ibm.websphere.objectgrid.ObjectGridManager;
import com.ibm.websphere.objectgrid.ObjectGridManagerFactory;
import com.ibm.websphere.objectgrid.security.config.ClientSecurityConfiguration;
import com.ibm.websphere.objectgrid.security.config.ClientSecurityConfigurationFactory;
import com.ibm.websphere.objectgrid.security.plugins.builtins.UserPasswordCredentialGenerator;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * 本システム全体のキャッシュ管理を行うクラスです.
 * TODO リファクタリング
 *
 * @author ryozo
 */
public class CacheManager<K, V> {

    Cache<K, V> cache;

    /**
     * applicationContext.xmlによって差し替える.
     * TODO mavenのprofileで差し替える.
     */
    @Setter
    @Getter
    private boolean enable = false;  // default

    /**
     * cacheの初期化を行います.
     * springによってアプリケーション起動時に一回だけ実行されます.
     *
     * @throws Exception
     */
    public void init() throws Exception {
        if (!enable) {
            cache = new DummyCache<>();
            return;
        }

        Map env = System.getenv();
        VcapCacheCredential credential = loadVcap((String) env.get("VCAP_SERVICES"));
        if (credential == null) {
            // CacheをONにしているにも関わらずVCAPが読み取れない場合はエラーとする.
            throw new IllegalStateException("can't read environment variable 'VCAP_SERVICES'");
        }

        ObjectGridManager ogm = ObjectGridManagerFactory.getObjectGridManager();
        ClientSecurityConfiguration csc = createClientSecurityConfiguration(credential);

        ClientClusterContext ccc = ogm.connect(credential.getCatalogEndPoint(), csc, null);
        cache = new DataCache<>(ogm.getObjectGrid(ccc, credential.getGridName()));
    }

    private ClientSecurityConfiguration createClientSecurityConfiguration(VcapCacheCredential credential) {
        ClientSecurityConfiguration csc = ClientSecurityConfigurationFactory.getClientSecurityConfiguration();
        csc.setCredentialGenerator(new UserPasswordCredentialGenerator(
                credential.getUsername(), credential.getPassword()));
        csc.setSecurityEnabled(true);
        return csc;
    }

    /**
     * {@code VCAP_SERVICES}を読み取ります.
     * @param vcap
     * @return
     */
    private VcapCacheCredential loadVcap(String vcap) {
        if (!StringUtils.hasLength(vcap)) {
            return null;
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode vcapNodes = mapper.readTree(vcap);

            // JDK7なのでStreamAPIは使わない
            Iterator<Map.Entry<String, JsonNode>> nodeIte = vcapNodes.fields();
            while (nodeIte.hasNext()) {
                Map.Entry<String, JsonNode> nodeEntry = nodeIte.next();
                if (nodeEntry.getKey().startsWith("DataCache")) {
                    JsonNode credentialNode = nodeEntry.getValue().get(0).get("credentials");
                    VcapCacheCredential credential = mapper.treeToValue(credentialNode, VcapCacheCredential.class);
                    return credential;
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return null;
    }

    /**
     * Bluemix DataCacheサービスのCredential情報を保持するクラスです.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    private static class VcapCacheCredential {
        private String username;
        private String password;
        private String catalogEndPoint;
        private String gridName;
    }

    /**
     * キャッシュを取得します.
     * @return
     */
    public Cache<K, V> getCache() {
        return cache;
    }
}
