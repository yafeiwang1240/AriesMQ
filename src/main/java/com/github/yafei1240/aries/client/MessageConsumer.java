package com.github.yafei1240.aries.client;

public interface MessageConsumer<T> {
    // 接收消费者数据
    void consume(T record);
}
