package com.github.caiyucong.wechatbot.handler.impl;

import com.github.caiyucong.wechatbot.domain.Member;
import com.github.caiyucong.wechatbot.domain.Payload;
import com.github.caiyucong.wechatbot.handler.AuthenticationHandler;
import com.github.caiyucong.wechatbot.handler.SystemMessageHandler;
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
    public void handler(Payload payload) {
        JsonObject jsonObject = JsonParser.parseString(payload.getContent().toString()).getAsJsonObject();
        String type = payload.getType();
        if (authenticationHandler == null) {
            authenticationHandler = new AuthenticationHandlerImpl(redisTemplate);
        }
        if ("system_event_login".equals(type)) {
            JsonObject userJson = jsonObject.get("user").getAsJsonObject();
            Gson gson = new Gson();
            Member member = gson.fromJson(userJson, Member.class);
            authenticationHandler.login(member);
        }
        if ("system_event_logout".equals(type)) {
            authenticationHandler.logout();
        }
    }

}
