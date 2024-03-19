package com.github.caiyucong.wechatbot.handler;

import com.github.caiyucong.wechatbot.domain.Member;

public interface AuthenticationHandler {

    void login(Member member);

    void logout();

    Member getLoginUser();
}
