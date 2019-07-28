package com.github.yafei1240.aries.client.record;

public class RecordMetaData {

    private static final int INVALID_OFFSET = -1;

    private String topic;
    private Long timestamp;
    private long offset = INVALID_OFFSET;

    public RecordMetaData(String topic, Long timestamp, long offset) {
        this.timestamp = timestamp;
        this.offset = offset;
        this.topic = topic;
    }

    public String topic() {
        return topic;
    }

    public Long timestamp() {
        return timestamp;
    }

    public boolean hasOffset() {
        return offset != INVALID_OFFSET;
    }

    public long offset() {
        return offset;
    }

}
