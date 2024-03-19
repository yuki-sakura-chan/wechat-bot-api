package com.github.caiyucong.wechatbot.domain;

import lombok.Data;

import java.util.List;

@Data
public class RoomPayload {

    private String id;

    /**
     * 管理员id列表
     */
    private List<String> adminIdList;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 成员列表
     */
    private List<MemberInfo> memberList;

}
