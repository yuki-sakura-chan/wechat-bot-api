package caiyucong.cn.bot.handler.impl;

import caiyucong.cn.bot.domain.Payload;
import caiyucong.cn.bot.domain.SingleMessage;
import caiyucong.cn.bot.handler.IsMentionedHandler;
import caiyucong.cn.bot.handler.MessageHandler;
import caiyucong.cn.bot.handler.RoomMessageHandler;
import caiyucong.cn.bot.handler.TrigConditionsHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class RoomMessageHandlerImpl implements RoomMessageHandler {

    private final IsMentionedHandler isMentionedHandler;

    private final ApplicationContext applicationContext;

    @Autowired
    public RoomMessageHandlerImpl(IsMentionedHandler isMentionedHandler, ApplicationContext applicationContext) {
        this.isMentionedHandler = isMentionedHandler;
        this.applicationContext = applicationContext;
    }

    @Override
    public MessageHandler useHandler(Payload payload) {
        if ("1".equals(payload.getIsMentioned())) {
            return isMentionedHandler;
        }
        Map<String, TrigConditionsHandler> beansOfType = applicationContext.getBeansOfType(TrigConditionsHandler.class);
        return this;
    }

    @Override
    public void messageReceivingAfter(Payload payload) {
        log.info("收到了来自群聊的消息");
    }

    @Override
    public void messageSendBefore(Payload payload) {

    }

    @Override
    public boolean messageSend(SingleMessage singleMessage) {
        return false;
    }

    @Override
    public void messageSendAfter(Payload payload) {

    }
}
