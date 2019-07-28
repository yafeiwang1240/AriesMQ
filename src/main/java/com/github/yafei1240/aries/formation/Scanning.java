package com.github.yafei1240.aries.formation;

import com.github.yafei1240.aries.model.ManagerPack;
import com.github.yafei1240.aries.client.record.ProducerRecord;

import java.util.concurrent.Future;

public interface Scanning {
    <T> Future<T> invoke(ManagerPack pack, ProducerRecord record, T result);
}
