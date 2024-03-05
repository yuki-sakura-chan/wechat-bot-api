package caiyucong.cn.bot.handler.impl;

import caiyucong.cn.bot.domain.Payload;
import caiyucong.cn.bot.domain.SingleMessage;
import caiyucong.cn.bot.handler.ToMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ToMessageHandlerImpl implements ToMessageHandler {

    @Override
    public void messageReceivingAfter(Payload payload) {
        log.info("收到来自个人的消息");
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
