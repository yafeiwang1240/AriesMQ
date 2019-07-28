package com.github.yafei1240.aries.exception;

public class InvalidTopicException extends Exception {
    public InvalidTopicException(String msg) {
        super(msg);
    }

    public InvalidTopicException(String msg, Throwable t) {
        super(msg, t);
    }
}
