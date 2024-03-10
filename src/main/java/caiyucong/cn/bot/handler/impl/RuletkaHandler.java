package caiyucong.cn.bot.handler.impl;

import caiyucong.cn.bot.domain.Message;
import caiyucong.cn.bot.domain.Payload;
import caiyucong.cn.bot.domain.Revolver;
import caiyucong.cn.bot.handler.IsMentionedHandler;
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

    @Override
    public boolean messageSend(Payload payload, Message singleMessage) {
        Revolver revolver = (Revolver) redisTemplate.opsForValue().get("revolver");
        if (Objects.isNull(revolver)) {
            revolver = new Revolver();
            revolver.loadBullets(1, 6);
            revolver.messUp();
            redisTemplate.opsForValue().set("revolver", revolver);
            singleMessage.addMessage("""
                    ✨俄罗斯转盘，游戏开始！✨
                    输入“开枪或者開槍”参与游戏""");
            return true;
        }
        return false;
    }

    @Override
    public boolean match(String content) {
        return content.equals("俄罗斯转盘");
    }
}
