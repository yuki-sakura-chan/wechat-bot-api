package caiyucong.cn.bot.domain;

import lombok.Data;

@Data
public class Member {

    private String id;

    private MemberInfo payload;

    private Object _events;

    private Integer _eventCount;

}
