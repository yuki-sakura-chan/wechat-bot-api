package caiyucong.cn.bot.handler.impl;

import caiyucong.cn.bot.domain.Payload;
import caiyucong.cn.bot.domain.SingleMessage;
import caiyucong.cn.bot.handler.IsMentionedHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RuletkaHandler implements IsMentionedHandler {


    @Override
    public void messageReceivingAfter(Payload payload) {
        
    }

    @Override
    public boolean messageSend(SingleMessage singleMessage) {
        return false;
    }

    @Override
    public boolean match(String content) {
        return true;
    }
}
