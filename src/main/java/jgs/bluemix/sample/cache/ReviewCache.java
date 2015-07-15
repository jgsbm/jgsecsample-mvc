package jgs.bluemix.sample.cache;

import jgs.bluemix.sample.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Reviewのキャッシュ実装です.
 * TODO リファクタリング。特にGenerics関連
 *
 * @author ryozo
 */
@Component
public class ReviewCache {

    @Autowired
    CacheManager<String, List<Review>> cacheManager;

    /** cacheの実体 */
    Cache<String, List<Review>> cache;

    void init() {
        cache = cacheManager.getCache();
    }

    public List<Review> get(String key) {
        return cache.get(key);
    }

    public void put(String key, List<Review> value) {
        cache.put(key, value);
    }

    public void remove(String key) {
        cache.remove(key);
    }

    public void clear() {
        cache.clear();
    }
}
