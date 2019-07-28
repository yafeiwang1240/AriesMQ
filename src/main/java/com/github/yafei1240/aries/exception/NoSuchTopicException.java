package com.github.yafei1240.aries.exception;

public class NoSuchTopicException extends Exception {
    public NoSuchTopicException(String msg) {
        super(msg);
    }

    public NoSuchTopicException(String msg, Throwable t) {
        super(msg, t);
    }
}
