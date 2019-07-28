package com.github.yafei1240.aries.client;

import com.github.yafei1240.aries.client.record.CustomerRecord;

import java.io.Closeable;

public interface Customer<K, V> extends Closeable {
   // do nothing
   void consume(CustomerRecord<K, V> customerRecord);
}
