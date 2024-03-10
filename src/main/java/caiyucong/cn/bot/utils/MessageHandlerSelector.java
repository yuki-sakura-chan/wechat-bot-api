package caiyucong.cn.bot.utils;

import caiyucong.cn.bot.domain.Payload;
import caiyucong.cn.bot.handler.IsMentionedHandler;
import caiyucong.cn.bot.handler.MessageHandler;
import caiyucong.cn.bot.handler.RoomMessageHandler;
import caiyucong.cn.bot.handler.ToMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
public class MessageHandlerSelector {

    private final ApplicationContext applicationContext;

    @Autowired
    public MessageHandlerSelector(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 获取各种触发条件能对的上的处理器
     *
     * @param payload payload
     * @return {@link MessageHandler}
     */
    public MessageHandler getHandler(Payload payload) {
        Object content = payload.getContent();
        MessageHandler messageHandler = null;
        if (content instanceof String) {
            String message = content.toString();
            int i = message.indexOf(" ");
            if (i > 0) {
                message = message.substring(i + 1);
            }
            Map<String, ? extends MessageHandler> handlerMap = filter(payload);
            for (MessageHandler handler : handlerMap.values()) {
                if (handler.match(message)) {
                    messageHandler = handler;
                    break;
                }
            }
        }
        return messageHandler;
    }

    private Map<String, ? extends MessageHandler> filter(Payload payload) {
        if (!Objects.isNull(payload.getObj().getFrom())) {
            if ("1".equals(payload.getIsMentioned())) {
                return applicationContext.getBeansOfType(IsMentionedHandler.class);
            }
            return applicationContext.getBeansOfType(RoomMessageHandler.class);
        } else {
            return applicationContext.getBeansOfType(ToMessageHandler.class);
        }
    }

}
