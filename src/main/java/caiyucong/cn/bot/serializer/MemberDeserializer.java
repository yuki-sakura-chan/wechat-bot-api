package caiyucong.cn.bot.serializer;

import caiyucong.cn.bot.domain.Member;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class MemberDeserializer extends JsonDeserializer<Member> {
    @Override
    public Member deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        return new Member();
    }

}
