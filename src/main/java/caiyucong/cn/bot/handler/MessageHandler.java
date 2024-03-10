package caiyucong.cn.bot.handler;

import caiyucong.cn.bot.domain.Payload;
import caiyucong.cn.bot.domain.SingleMessage;

public interface MessageHandler {

    void messageReceivingAfter(Payload payload);

    boolean messageSend(SingleMessage singleMessage);

    /**
     * 匹配字符，优先选择容器中第一个处理器。
     * @param content 消息内容
     * @return boolean
     */
    boolean match(String content);

}
