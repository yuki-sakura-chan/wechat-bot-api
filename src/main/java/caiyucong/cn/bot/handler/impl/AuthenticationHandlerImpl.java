package caiyucong.cn.bot.handler.impl;

import caiyucong.cn.bot.domain.Member;
import caiyucong.cn.bot.handler.AuthenticationHandler;
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
