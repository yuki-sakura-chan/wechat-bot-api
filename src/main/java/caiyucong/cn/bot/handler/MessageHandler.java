package caiyucong.cn.bot.handler;

import caiyucong.cn.bot.domain.Payload;
import caiyucong.cn.bot.domain.SingleMessage;

public interface MessageHandler {

    void messageReceivingAfter(Payload payload);

    void messageSendBefore(Payload payload);

    boolean messageSend(SingleMessage singleMessage);

    void messageSendAfter(Payload payload);

    default MessageHandler useHandler(Payload payload){
        return this;
    }

}
