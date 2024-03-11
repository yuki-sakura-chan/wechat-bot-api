package caiyucong.cn.bot.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Optional;

@Data
@Slf4j
public class Payload {

    /**
     * <p>功能类型</p>
     * <li>✅ 文字(text)</li>
     * <li>✅ 链接卡片(urlLink)</li>
     * <li>✅ 图片(file)</li>
     * <li>✅ 视频(file)</li>
     * <li>✅ 附件(file)</li>
     * <li>✅ 语音(file)</li>
     * <li>✅ 添加好友邀请(friendship)</li>
     * <p>其他类型</p>
     * <li>未实现的消息类型(unknown)</li>
     * <p>系统类型</p>
     * <li>✅ 登录(system_event_login)</li>
     * <li>✅ 登出(system_event_logout)</li>
     * <li>✅ 异常报错(system_event_error)</li>
     * <li>✅ 快捷回复后消息推送状态通知(system_event_push_notify)</li>
     */
    private String type;

    /**
     * 传输的内容, 文本或传输的文件共用这个字段，结构映射请看示例
     */
    private Object content;

    /**
     * 消息的相关发送方数据, JSON String
     */
    private String source;

    /**
     * 解析后的 source
     */
    private SourceData obj;

    /**
     * 该消息是@我的消息
     */
    private String isMentioned;

    /**
     * 是否来自系统的消息
     */
    private String isSystemEvent;

    /**
     * 是否是来自自己的消息
     */
    private String isMsgFromSelf;

    public void setSource(String source) {
        this.source = source;
        long begin = System.currentTimeMillis();
        SourceData sourceData = SourceData.buildSource(source);
        long end = System.currentTimeMillis();
        log.info("json解析花了：{}ms", end - begin);
        obj = sourceData;
    }

    public String getMessage(){
        if (content instanceof String) {
            if (((String) content).contains("<msg>")) {
                return "[动画表情]";
            }
            return content.toString();
        }
        return "[图片]";
    }

    public String getMemberId() {
        return Optional.ofNullable(obj.getFrom()).orElse(new Member()).getId();
    }

    public String getMemberName(){
        return fromIsNotnull() ? obj.getFrom().getPayload().getName() : null;
    }

    public String getMemberAvatar(){
        return fromIsNotnull() ? obj.getFrom().getPayload().getAvatar() : null;
    }

    public Integer getMemberGender(){
        return fromIsNotnull() ? obj.getFrom().getPayload().getGender() : null;
    }

    public String getMemberAlias(){
        return fromIsNotnull() ? obj.getFrom().getPayload().getAlias() : null;
    }

    private boolean fromIsNotnull(){
        return !Objects.isNull(obj.getFrom());
    }


}
