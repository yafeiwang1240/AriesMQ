package com.github.yafei1240.aries.exception;

public class InvalidTopicExcepiton extends Exception {
    public InvalidTopicExcepiton(String msg) {
        super(msg);
    }

    public InvalidTopicExcepiton(String msg, Throwable t) {
        super(msg, t);
    }
}
