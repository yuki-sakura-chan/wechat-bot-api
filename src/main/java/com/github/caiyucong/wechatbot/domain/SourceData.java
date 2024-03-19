package com.github.caiyucong.wechatbot.domain;

import com.google.gson.Gson;
import lombok.Data;

@Data
public class SourceData {

    /**
     * 消息来自群，会有以下对象，否则为空字符串
     */
    private Room room;

    /**
     * 消息来自个人，会有以下对象，否则为空字符串
     */
    private Member to;

    /**
     * 消息发送方
     */
    private Member from;

    private SourceData(){}

    public static SourceData buildSource(String json) {
        SourceData sourceData = new SourceData();
        Gson gson = new Gson();
        Source source = gson.fromJson(json, Source.class);
        if (!"".equals(source.getRoom())) {
            String roomJson = gson.toJson(source.getRoom());
            sourceData.room = gson.fromJson(roomJson, Room.class);
        }
        if (!"".equals(source.getTo())) {
            String toJson = gson.toJson(source.getTo());
            sourceData.to = gson.fromJson(toJson, Member.class);
        }
        if (!"".equals(source.getFrom())) {
            String fromJson = gson.toJson(source.getFrom());
            sourceData.from = gson.fromJson(fromJson, Member.class);
        }
        return sourceData;
    }

    @Data
    static class Source {
        private Object room;

        private Object to;

        private Object from;
    }

}
