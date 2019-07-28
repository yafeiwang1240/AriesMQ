package com.github.yafei1240.aries.client.record;

public interface CallBack {
    <T> void invoke(T result, Exception exception);
}
