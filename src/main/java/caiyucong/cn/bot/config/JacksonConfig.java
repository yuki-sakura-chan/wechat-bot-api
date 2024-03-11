package caiyucong.cn.bot.config;

import caiyucong.cn.bot.domain.Member;
import caiyucong.cn.bot.domain.Room;
import caiyucong.cn.bot.serializer.MemberDeserializer;
import caiyucong.cn.bot.serializer.RoomDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer addCustomDeserializer() {
        return builder -> {
            SimpleModule module = new SimpleModule();
            module.addDeserializer(Room.class, new RoomDeserializer());
            module.addDeserializer(Member.class, new MemberDeserializer());
            builder.modules(module);
        };
    }

}
