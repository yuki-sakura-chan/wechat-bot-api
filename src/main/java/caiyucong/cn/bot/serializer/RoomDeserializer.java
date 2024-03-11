package caiyucong.cn.bot.serializer;

import caiyucong.cn.bot.domain.Room;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class RoomDeserializer extends JsonDeserializer<Room> {
    @Override
    public Room deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        return new Room();
    }
}
