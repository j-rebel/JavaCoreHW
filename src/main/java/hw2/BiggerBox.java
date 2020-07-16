package hw2;

public class BiggerBox<K, V> {

    private K key;
    private V value;

    public BiggerBox(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BiggerBox{" +
                "key=" + key +
                "; keyType=" + key.getClass().getName() +
                ", value=" + value +
                "; valueType=" + value.getClass().getName() +
                '}';
    }
}
