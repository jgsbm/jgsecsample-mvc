package jgs.bluemix.sample.cache;

import com.ibm.websphere.objectgrid.ObjectGrid;
import com.ibm.websphere.objectgrid.ObjectGridException;
import jgs.bluemix.sample.exception.DataCacheRuntimeException;

/**
 * DataCacheサービスを利用する場合のCacheサービスを提供するクラスです.
 * @param <K> cacheキー
 * @param <V> cache値
 */
public class DataCache<K, V> implements Cache<K, V> {

    /** Cacheの実体 */
    private ObjectGrid grid;

    // TODO 設定ファイル上である程度操作できるようにする？
    private static final String MAP_SUFFIX = "Map.NONE.O";

    DataCache(ObjectGrid grid) {
        this.grid = grid;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V get(Object K) {
        try {
            return (V) grid.getSession().getMap(getMapName()).get(K);
        } catch (ObjectGridException e) {
            throw new DataCacheRuntimeException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(K key, V value) {
        try {
            grid.getSession().getMap(getMapName()).put(key, value);
        } catch (ObjectGridException e) {
            throw new DataCacheRuntimeException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(K key) {
        try {
            grid.getSession().getMap(getMapName()).remove(key);
        } catch (ObjectGridException e) {
            throw new DataCacheRuntimeException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        try {
            grid.getSession().getMap(getMapName()).clear();
        } catch (ObjectGridException e) {
            throw new DataCacheRuntimeException(e);
        }
    }

    /**
     * SessionからMapを取得する際のMap名を取得します.
     * @return Map名
     */
    private String getMapName() {
        return grid.getName() + MAP_SUFFIX;
    }
}
