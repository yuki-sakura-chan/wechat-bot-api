package com.github.caiyucong.wechatbot.handler;

import com.github.caiyucong.wechatbot.domain.Message;
import com.github.caiyucong.wechatbot.domain.Payload;

public interface MessageHandler {

    default void messageReceivingAfter(Payload payload) {}

    boolean messageSend(Payload payload, Message message);

    /**
     * 匹配字符，优先选择容器中第一个处理器。
     *
     * @param content 消息内容
     * @return boolean
     */
    boolean match(String content);

}
