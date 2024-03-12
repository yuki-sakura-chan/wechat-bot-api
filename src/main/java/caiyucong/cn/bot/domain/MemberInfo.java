package caiyucong.cn.bot.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MemberInfo implements Serializable {


    private String id;

    /**
     * 昵称
     */
    private String name;

    /**
     * 备注
     */
    private String alias;

    /**
     * 头像
     */
    private String avatar;


    /**
     * 是否是朋友
     */
    private Boolean friend;

    /**
     * 性别
     */
    private Integer gender;

    private List<String> phone;

    /**
     * 签名
     */
    private String signature;

    private Boolean star;

    private Integer type;

}
