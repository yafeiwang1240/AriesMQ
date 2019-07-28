package com.github.yafei1240.aries.client.impl;

import com.github.yafei1240.aries.client.Admin;
import com.github.yafei1240.aries.client.record.NewTopic;
import com.github.yafei1240.aries.model.CreateTopicsResult;
import com.github.yafei1240.aries.model.DeleteTopicsResult;
import com.github.yafei1240.aries.server.BrokerServer;

import java.util.Collection;

public class AriesAdmin implements Admin {

    @Override
    public CreateTopicsResult createTopics(Collection<NewTopic> topics) {
        return BrokerServer.createTopics(topics);
    }

    @Override
    public DeleteTopicsResult deleteTopics(Collection<NewTopic> topics) {
        return BrokerServer.deleteTopics(topics);
    }
}
