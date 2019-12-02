package DataStructure.Tree;

public class Entry<K, V> {
    public K key;
    public V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public Entry(Entry<K, V> entry) {
        this.key = entry.key;
        this.value = entry.value;
    }

    public Entry() {
    }
}
