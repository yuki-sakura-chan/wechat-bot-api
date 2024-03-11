package caiyucong.cn.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootApplication
@Slf4j
public class BotApplication {

    public static void main(String[] args) {
        SpringApplication.run(BotApplication.class, args);
        log.info("程序启动成功！！！！！！");
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //连接工厂
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setDefaultSerializer(new JdkSerializationRedisSerializer());
        //键序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //key hashMap序列化
        redisTemplate.setHashKeySerializer(new JdkSerializationRedisSerializer());
        return redisTemplate;
    }

}
