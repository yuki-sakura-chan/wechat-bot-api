package caiyucong.cn.bot.controller;

import caiyucong.cn.bot.domain.Member;
import caiyucong.cn.bot.domain.Payload;
import caiyucong.cn.bot.domain.R;
import caiyucong.cn.bot.domain.SingleMessage;
import caiyucong.cn.bot.handler.MessageHandler;
import caiyucong.cn.bot.handler.RoomMessageHandler;
import caiyucong.cn.bot.handler.ToMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("webhook/msg")
@Slf4j
public class MsgController {

    private final ToMessageHandler toMessageHandler;

    private final RoomMessageHandler roomMessageHandler;

    @Autowired
    public MsgController(ToMessageHandler toMessageHandler,
                         @Qualifier("roomMessageHandlerImpl") RoomMessageHandler roomMessageHandler) {
        this.toMessageHandler = toMessageHandler;
        this.roomMessageHandler = roomMessageHandler;
    }

    @PostMapping("v1")
    public R<?> v1(Payload payload) {
        String message = payload.getMessage();
        Member from = payload.getObj().getFrom();
        String name = "你自己";
        String id = "";
        if (!Objects.isNull(from)) {
            name = from.getPayload().getName();
            id = from.getId();
        }
        log.info("微信用户：“{}”发送了消息:“{}”，Ta的id是：{}", name, message, id);
        MessageHandler messageHandler;
        if (Objects.isNull(payload.getObj().getRoom())) {
            messageHandler = toMessageHandler;
        } else {
            messageHandler = roomMessageHandler;
        }
        messageHandler = messageHandler.useHandler(payload);
        messageHandler.messageReceivingAfter(payload);
        messageHandler.messageSendBefore(payload);
        SingleMessage singleMessage = new SingleMessage();
        boolean flag = messageHandler.messageSend(singleMessage);
        messageHandler.messageSendAfter(payload);
        return flag ? R.success(singleMessage) : R.notRespond();
    }

}
