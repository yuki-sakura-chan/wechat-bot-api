package com.github.caiyucong.wechatbot.domain;

import lombok.Data;

import java.util.List;

@Data
public class Message {

    private List<SingleMessage> singleMessages;

    public void addMessage(String content) {
        SingleMessage singleMessage = new SingleMessage();
        singleMessage.setContent(content);
        singleMessages.add(singleMessage);
    }

    public void addMessage(String type, String content) {
        SingleMessage singleMessage = new SingleMessage();
        singleMessage.setContent(content);
        singleMessage.setType(type);
        singleMessages.add(singleMessage);
    }

}
