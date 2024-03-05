package caiyucong.cn.bot.domain;

import lombok.Data;

@Data
public class Room {

    private String id;

    private String topic;

    private RoomPayload payload;

    /**
     * 以下暂不清楚什么用途，如有兴趣，请查阅 wechaty 官网文档
     */
    private Object _events;

    private Integer _eventsCount;

}
