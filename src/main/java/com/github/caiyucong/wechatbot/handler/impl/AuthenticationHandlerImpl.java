package com.github.caiyucong.wechatbot.handler.impl;

import com.github.caiyucong.wechatbot.domain.Member;
import com.github.caiyucong.wechatbot.handler.AuthenticationHandler;
import org.springframework.data.redis.core.RedisTemplate;

public class AuthenticationHandlerImpl implements AuthenticationHandler {

    private final RedisTemplate<String,Object> redisTemplate;

    public AuthenticationHandlerImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Override
    public void login(Member member) {
        redisTemplate.opsForValue().set("wechatBot:loginUser", member);
    }

    @Override
    public void logout() {
        redisTemplate.delete("wechatBot:loginUser");
    }

    @Override
    public Member getLoginUser() {
        return (Member) redisTemplate.opsForValue().get("wechatBot:loginUser");
    }
}
