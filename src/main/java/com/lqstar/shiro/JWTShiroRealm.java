package com.lqstar.shiro;

import com.alibaba.fastjson.JSON;
import com.lqstar.model.TokenBean;
import com.lqstar.model.User;
import com.lqstar.service.LoginService;
import com.lqstar.utils.JwtUtils;
import com.lqstar.utils.RedisConstants;
import com.lqstar.utils.RedisUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 自定义身份认证
 * 基于HMAC（ 散列消息认证码）的控制域
 */

public class JWTShiroRealm extends AuthorizingRealm {
    private final Logger log = LoggerFactory.getLogger(JWTShiroRealm.class);
    @Autowired
    public LoginService loginService;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    RedisConstants redisConstants;

    public JWTShiroRealm() {
        //这里使用我们自定义的Matcher
        this.setCredentialsMatcher(new JWTCredentialsMatcher());
    }

    /**
     * 限定这个Realm只支持我们自定义的JWT Token
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
//        return true;
    }

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authcToken;
        String token = jwtToken.getToken();
        System.out.println("请求：" + token);
        //根据token获取到username，从而判断token是否过期
        String tokenName = JwtUtils.getUsername(token);
//        User user = loginService.findSaltByName(na);
        Object obj = redisUtil.get(tokenName);
        if (obj == null) {
            throw new AuthenticationException("token过期，请重新登录");
        }
        TokenBean user = JSON.parseObject(obj.toString(), TokenBean.class);

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,user.getSalt() , "jwtRealm");

        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return new SimpleAuthorizationInfo();
    }
}
