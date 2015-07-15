package jgs.bluemix.sample.cache;

/**
 * cacheの操作を表現するインタフェースです.
 *
 * @param <K> cacheキー
 * @param <V> cache値
 * @author ryozo
 */
public interface Cache<K, V> {

    /**
     * キャッシュ値を取得します.
     * @param key キー
     * @return キャッシュ値
     */
    V get(K key);

    /**
     * キャッシュに値を設定します.
     * @param key キー
     * @param value キャッシュ値
     */
    void put(K key, V value);

    /**
     * 所定のキーを保持するキャッシュを削除します.
     * @param key キャッシュ
     */
    void remove(K key);

    /**
     * キャッシュの内容全件をクリアします.
     */
    void clear();

}
