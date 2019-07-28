package com.github.yafei1240.aries.model;

public class TopicResult {

    public enum Status {
        SUCCESS,
        FAILED,
    }

    public static TopicResult newTopicResult(TopicResult.Status status, String message) {
        return new TopicResult(status, message);
    }

    private Status status;
    private String message;

    public TopicResult(Status status, String message) {
        this.message = message;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
