package com.lqstar.shiro;

import org.apache.shiro.authc.HostAuthenticationToken;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/4/28/028 22:54
 */
public class JwtToken implements HostAuthenticationToken {
    private String token;
    private String host;
    public JwtToken(String token) {
        this(token, null);
    }
    public JwtToken(String token, String host) {
        this.token = token;
        this.host = host;
    }
    public String getToken(){
        return this.token;
    }
    public String getHost() {
        return host;
    }
    @Override
    public Object getPrincipal() {
        return token;
    }
    @Override
    public Object getCredentials() {
        return token;
    }
    @Override
    public String toString(){
        return token + ':' + host;
    }
}
