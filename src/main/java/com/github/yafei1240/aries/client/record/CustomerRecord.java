package com.github.yafei1240.aries.client.record;

public class CustomerRecord<K, V> {
    private String topic;
    private K key;
    private V value;
    private Long timestamp;

    public CustomerRecord(String topic, K key, V value, Long timestamp) {
        this.topic = topic;
        this.key = key;
        this.value = value;
        this.timestamp = timestamp;
    }

    public K key() {
        return key;
    }

    public String topic() {
        return topic;
    }

    public V value() {
        return value;
    }

    public Long timestamp() {
        return timestamp;
    }
}
