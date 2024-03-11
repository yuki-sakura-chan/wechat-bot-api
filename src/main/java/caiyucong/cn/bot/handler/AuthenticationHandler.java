package caiyucong.cn.bot.handler;

import caiyucong.cn.bot.domain.Member;

public interface AuthenticationHandler {

    void login(Member member);

    void logout();

    Member getLoginUser();
}
