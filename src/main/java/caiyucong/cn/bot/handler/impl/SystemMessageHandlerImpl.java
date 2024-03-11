package caiyucong.cn.bot.handler.impl;

import caiyucong.cn.bot.domain.Member;
import caiyucong.cn.bot.handler.AuthenticationHandler;
import caiyucong.cn.bot.handler.SystemMessageHandler;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class SystemMessageHandlerImpl implements SystemMessageHandler {

    private AuthenticationHandler authenticationHandler;

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public SystemMessageHandlerImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    public void setAuthenticationHandler(@Nullable AuthenticationHandler authenticationHandler) {
        this.authenticationHandler = authenticationHandler;
    }

    @Override
    public void handler(String content) {
        JsonObject jsonObject = JsonParser.parseString(content).getAsJsonObject();
        if (authenticationHandler == null) {
            authenticationHandler = new AuthenticationHandlerImpl(redisTemplate);
        }
        String event = jsonObject.get("event").getAsString();
        if ("login".equals(event)) {
            JsonObject userJson = jsonObject.get("user").getAsJsonObject();
            Gson gson = new Gson();
            Member member = gson.fromJson(userJson, Member.class);
            authenticationHandler.login(member);
        }
        if ("logout".equals(event)) {
            authenticationHandler.logout();
        }
    }

}
