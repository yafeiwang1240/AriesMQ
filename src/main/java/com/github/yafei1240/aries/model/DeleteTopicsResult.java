package com.github.yafei1240.aries.model;

import java.util.Map;

public class DeleteTopicsResult {

    public Map<String, TopicResult> resultMap;

    public DeleteTopicsResult(Map<String, TopicResult> resultMap) {
        this.resultMap = resultMap;
    }
}
