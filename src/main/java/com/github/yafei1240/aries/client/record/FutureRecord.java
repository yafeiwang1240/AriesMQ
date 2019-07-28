package com.github.yafei1240.aries.client.record;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class FutureRecord<T> extends FutureTask<T> {

    public FutureRecord(Callable<T> callable) {
        super(callable);
    }

    public FutureRecord(Runnable runnable, T result) {
        super(runnable, result);
    }
}
