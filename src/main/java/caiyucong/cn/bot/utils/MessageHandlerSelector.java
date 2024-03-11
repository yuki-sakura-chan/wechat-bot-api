package caiyucong.cn.bot.utils;

import caiyucong.cn.bot.domain.Payload;
import caiyucong.cn.bot.handler.*;
import caiyucong.cn.bot.handler.impl.AuthenticationHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
public class MessageHandlerSelector {

    private final ApplicationContext applicationContext;

    private final RedisTemplate<String,Object> redisTemplate;

    private AuthenticationHandler authenticationHandler;

    @Autowired
    public void setAuthenticationHandler(@Nullable AuthenticationHandler authenticationHandler) {
        this.authenticationHandler = authenticationHandler;
    }

    @Autowired
    public MessageHandlerSelector(ApplicationContext applicationContext, RedisTemplate<String, Object> redisTemplate) {
        this.applicationContext = applicationContext;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 获取各种触发条件能对的上的处理器
     *
     * @param payload payload
     * @return {@link MessageHandler}
     */
    public MessageHandler getHandler(Payload payload) {
        if (authenticationHandler == null) {
            authenticationHandler = new AuthenticationHandlerImpl(redisTemplate);
        }
        Object content = payload.getContent();
        MessageHandler messageHandler = null;
        if (content instanceof String) {
            String message = content.toString();
            String loginUserName = authenticationHandler.getLoginUser().getPayload().getName();
            message = message.replaceAll(" ", " ");
            String matchMessage = message.replaceAll(String.format("^@%s\\s+|^@%s", loginUserName, loginUserName), "");
            Map<String, ? extends MessageHandler> handlerMap = filter(payload);
            for (MessageHandler handler : handlerMap.values()) {
                if (handler.match(matchMessage)) {
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
