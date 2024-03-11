package caiyucong.cn.bot.handler.impl;

import caiyucong.cn.bot.domain.Message;
import caiyucong.cn.bot.domain.Payload;
import caiyucong.cn.bot.domain.Revolver;
import caiyucong.cn.bot.handler.RoomMessageHandler;
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
        }
        // 睡一觉清醒了，tmd这里还set了一下，靠！
        redisTemplate.opsForValue().set("revolver", revolver);
        return true;
    }

    @Override
    public boolean match(String content) {
        return "开枪".equals(content) || "開槍".equals(content);
    }

}
