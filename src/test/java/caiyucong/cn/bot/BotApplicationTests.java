package caiyucong.cn.bot;

import caiyucong.cn.bot.domain.Revolver;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class BotApplicationTests {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    BotApplicationTests(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Test
    void testAdd() {
        Revolver revolver = new Revolver();
        redisTemplate.opsForValue().set("key", revolver);
    }

    @Test
    void testRemove(){
        redisTemplate.delete("key");
    }

}
