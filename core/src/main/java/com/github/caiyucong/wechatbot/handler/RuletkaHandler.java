package com.github.caiyucong.wechatbot.handler;

import com.github.caiyucong.wechatbot.domain.Message;
import com.github.caiyucong.wechatbot.domain.Payload;
import com.github.caiyucong.wechatbot.domain.Revolver;
import com.github.caiyucong.wechatbot.exception.WechatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class RuletkaHandler implements IsMentionedHandler {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RuletkaHandler(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private Integer number = 1;

    private Integer magazineMax = 6;

    @Override
    public boolean messageSend(Payload payload, Message singleMessage) {
        Revolver revolver = (Revolver) redisTemplate.opsForValue().get("revolver");
        if (!Objects.isNull(revolver)) {
            return false;
        }
        revolver = new Revolver();
        try {
            revolver.loadBullets(number, magazineMax);
            revolver.messUp();
            redisTemplate.opsForValue().set("revolver", revolver);
            singleMessage.addMessage("""
                    ✨俄罗斯转盘，游戏开始！✨
                    输入“开枪或者開槍”参与游戏""");
        } catch (WechatException e) {
            log.info(e.getMessage(), e);
            singleMessage.addMessage(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean match(String content) {
        if (!content.matches("^俄罗斯转盘\\s+\\d+\\s+\\d+|^俄罗斯转盘")) {
            return false;
        }
        String[] split = content.split("\\s+");
        if (split.length == 1) {
            return true;
        }
        if (split.length == 2 && "结束".equals(split[1])) {
            return true;
        }
        if (split.length == 3) {
            number = Integer.valueOf(split[1]);
            magazineMax = Integer.valueOf(split[2]);
            return true;
        }
        return false;
    }
}
