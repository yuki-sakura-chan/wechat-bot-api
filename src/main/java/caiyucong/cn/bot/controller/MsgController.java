package caiyucong.cn.bot.controller;

import caiyucong.cn.bot.domain.Member;
import caiyucong.cn.bot.domain.Message;
import caiyucong.cn.bot.domain.Payload;
import caiyucong.cn.bot.domain.R;
import caiyucong.cn.bot.handler.MessageHandler;
import caiyucong.cn.bot.handler.SystemMessageHandler;
import caiyucong.cn.bot.utils.MessageHandlerSelector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping("webhook/msg")
@Slf4j
public class MsgController {

    private final MessageHandlerSelector messageHandlerSelector;

    private final SystemMessageHandler systemMessageHandler;

    @Autowired
    public MsgController(MessageHandlerSelector messageHandlerSelector, SystemMessageHandler systemMessageHandler) {
        this.messageHandlerSelector = messageHandlerSelector;
        this.systemMessageHandler = systemMessageHandler;
    }

    @PostMapping("v1")
    public R<?> v1(Payload payload) {
        if ("1".equals(payload.getIsSystemEvent())) {
            systemMessageHandler.handler(payload.getContent().toString());
            return R.notRespond();
        }
        String message = payload.getMessage();
        Member from = payload.getObj().getFrom();
        String name = "你自己";
        String id = "";
        if (!Objects.isNull(from)) {
            name = from.getPayload().getName();
            id = from.getId();
        }
        log.info("微信用户：“{}”发送了消息:“{}”，Ta的id是：{}", name, message, id);
        long beginTime = System.currentTimeMillis();
        MessageHandler messageHandler = messageHandlerSelector.getHandler(payload);
        long endTime = System.currentTimeMillis();
        log.info("获取处理器所花费的时间是：{}ms", endTime - beginTime);
        if (Objects.isNull(messageHandler)) {
            return R.notRespond();
        }
        messageHandler.messageReceivingAfter(payload);
        Message messageObject = new Message();
        messageObject.setSingleMessages(new ArrayList<>());
        boolean flag = messageHandler.messageSend(payload, messageObject);
        return flag ? R.success(messageObject.getSingleMessages()) : R.notRespond();
    }

}
