# 轻量级消息中间件

#### 支持订阅发布模式（生产者消费者模式，异步）

#### 支持观察者模式（同步消息中间件）



##### 接口说明

```java
/**
 * AriesMQ 管理员用户
 */
public interface Admin {

    /**
     * 创建topic
     * @param topics
     * @return
     */
    CreateTopicsResult createTopics(Collection<NewTopic> topics);

    /**
     * 删除topic
     * @param topics
     * @return
     */
    DeleteTopicsResult deleteTopics(Collection<NewTopic> topics);
}
```

```java
public interface MessageConsumer<T> {
    // 接收消费者数据
    void consume(T record);
}

/**
 * 实际消费者，实例需实现 {@link MessageConsumer}
 */
public interface Customer<K, V> extends Closeable {
   // do nothing
   void consume(CustomerRecord<K, V> customerRecord);
}
```

```java
public interface Producer<K, V> extends Closeable {
    /**
     * 异步消息发布
     * @param record
     * @return
     * @throws
     */
    Future<RecordMetaData> sendAsync(ProducerRecord<K, V> record) throws NoSuchTopicException;

    /**
     * 异步随机发送给一个消费者
     * @param record
     * @return
     */
    Future<RecordMetaData> sendAsyncRandom(ProducerRecord<K, V> record) throws NoSuchTopicException;

    /**
     * 带回调的异步消息发布
     * @param record
     * @param callBack
     * @return
     */
    Future<RecordMetaData> sendAsync(ProducerRecord<K, V> record, CallBack callBack) throws NoSuchTopicException;

    /**
     * 带回调的异步消息随机发布
     * @param record
     * @param callBack
     * @return
     */
    Future<RecordMetaData> sendAsyncRandom(ProducerRecord<K, V> record, CallBack callBack) throws NoSuchTopicException;

    /**
     * 同步发布消息
     * @param record
     * @return
     */
    RecordMetaData send(ProducerRecord<K, V> record) throws NoSuchTopicException;

    /**
     * 同步随机发布给一个消费者消息
     * @param record
     * @return
     */
    RecordMetaData sendRandom(ProducerRecord<K, V> record) throws NoSuchTopicException;
}
```

