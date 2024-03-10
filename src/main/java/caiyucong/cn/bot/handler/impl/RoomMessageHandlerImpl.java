package caiyucong.cn.bot.handler.impl;

import caiyucong.cn.bot.domain.Payload;
import caiyucong.cn.bot.domain.SingleMessage;
import caiyucong.cn.bot.handler.RoomMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RoomMessageHandlerImpl implements RoomMessageHandler {

    @Override
    public void messageReceivingAfter(Payload payload) {
        log.info("收到了来自群聊的消息");
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
