package com.lqstar.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/4/29/029 16:22
 */
public class CustomizedToken extends UsernamePasswordToken {
    private static final long serialVersionUID = -8994712492402637507L;

    private String loginType;

    public CustomizedToken() {
    }

    public CustomizedToken(final String username, final String password, final String loginType) {
        super(username, password);
        this.loginType = loginType;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

}
