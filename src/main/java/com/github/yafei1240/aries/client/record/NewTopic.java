package com.github.yafei1240.aries.client.record;

import com.github.yafei1240.aries.exception.InvalidTopicExcepiton;

public class NewTopic {
    private String name;

    public NewTopic(String name) {
        this.name = name;
    }

    private void newValidException() throws InvalidTopicExcepiton {
        if (name == null || name.trim().equals("")) {
            throw new InvalidTopicExcepiton("topic can not be empty");
        }
    }

    public String getName() {
        return name;
    }
}
