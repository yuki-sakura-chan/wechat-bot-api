package caiyucong.cn.bot;

import caiyucong.cn.bot.domain.Member;
import caiyucong.cn.bot.domain.MemberInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class BotApplicationTests {

    private final RedisTemplate<String,Object> redisTemplate;

    @Autowired
    BotApplicationTests(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Test
    void contextLoads() {
        Member member = new Member();
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setId("1");
        member.setPayload(memberInfo);
        member.set_events("test");
        redisTemplate.opsForValue().set("test", member);
    }

}
