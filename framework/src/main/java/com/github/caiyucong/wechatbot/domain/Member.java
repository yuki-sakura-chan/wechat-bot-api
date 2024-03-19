package com.github.caiyucong.wechatbot.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Member implements Serializable {

    private String id;

    private MemberInfo payload;

    private Object _events;

    private Integer _eventCount;

}
