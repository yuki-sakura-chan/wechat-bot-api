package caiyucong.cn.bot.handler.impl;

import caiyucong.cn.bot.domain.Payload;
import caiyucong.cn.bot.domain.SingleMessage;
import caiyucong.cn.bot.handler.IsMentionedHandler;
import caiyucong.cn.bot.handler.TrigConditionsHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class IsMentionedHandlerImpl implements IsMentionedHandler, TrigConditionsHandler {

    @Override
    public void messageReceivingAfter(Payload payload) {
        log.info("有人提及到我了，他的昵称是：{}", payload.getMemberName());
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
