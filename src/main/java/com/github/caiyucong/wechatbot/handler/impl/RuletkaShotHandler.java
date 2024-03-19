package com.github.caiyucong.wechatbot.handler.impl;

import com.github.caiyucong.wechatbot.domain.Message;
import com.github.caiyucong.wechatbot.domain.Payload;
import com.github.caiyucong.wechatbot.domain.Revolver;
import com.github.caiyucong.wechatbot.handler.RoomMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class RuletkaShotHandler implements RoomMessageHandler {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RuletkaShotHandler(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean messageSend(Payload payload, Message singleMessage) {
        String key = "revolver";
        Revolver revolver = (Revolver) redisTemplate.opsForValue().get(key);
        if (Objects.isNull(revolver)) {
            return false;
        }
        String memberName = payload.getMemberName();
        if (revolver.shot()) {
            singleMessage.addMessage(String.format("@%s 你中弹了！", memberName));
        } else {
            singleMessage.addMessage("枪声未响，游戏继续！");
        }
        List<Boolean> magazine = revolver.getMagazine();
        if (!magazine.contains(Boolean.TRUE)) {
            singleMessage.addMessage("弹匣打空，游戏结束！");
            redisTemplate.delete(key);
            return true;
        }
        redisTemplate.opsForValue().set("revolver", revolver);
        return true;
    }

    @Override
    public boolean match(String content) {
        return "开枪".equals(content) || "開槍".equals(content);
    }

}
