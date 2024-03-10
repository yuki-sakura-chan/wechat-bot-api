package caiyucong.cn.bot.handler;

import caiyucong.cn.bot.domain.Message;
import caiyucong.cn.bot.domain.Payload;

public interface MessageHandler {

    default void messageReceivingAfter(Payload payload) {}

    boolean messageSend(Payload payload, Message message);

    /**
     * 匹配字符，优先选择容器中第一个处理器。
     *
     * @param content 消息内容
     * @return boolean
     */
    boolean match(String content);

}
