package jgs.bluemix.sample.cache;

/**
 * キャッシュ機能を利用しない場合に適用されるダミー用のキャッシュサービスです.
 * 本クラスの操作は全て無視されます.(何ら作用を及ぼしません)
 *
 * @param <K> cacheキー
 * @param <V> cache値
 */
public class DummyCache<K, V> implements Cache<K, V>{

    /**
     * {@inheritDoc }
     */
    @Override
    public V get(K key) {
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void put(K key, V value) {
        return;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void remove(K key) {
        return;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void clear() {
        return;
    }
}
