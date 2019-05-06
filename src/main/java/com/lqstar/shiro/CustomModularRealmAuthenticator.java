package com.lqstar.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/4/29/029 14:10
 */

/**
 *  * Description:全局shiro拦截分发realm
 *  
 */
public class CustomModularRealmAuthenticator extends ModularRealmAuthenticator {

    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken)
            throws AuthenticationException

    {
// 判断getRealms()是否返回为空
        assertRealmsConfigured();
// 强制转换回自定义的MyLoginToken
        CustomizedToken token = (CustomizedToken) authenticationToken;
// 找到当前登录人的登录类型
        String loginType = token.getLoginType();
// 所有Realm
        Collection<Realm> realms = getRealms();
// 找到登录类型对应的指定Realm
        Collection<Realm> typeRealms = new ArrayList<Realm>();
        System.out.println("已注册realm：" + realms);
        for (Realm realm : realms) {
            if (realm.getName().toLowerCase().contains("myshirorealm")) {
                typeRealms.add(realm);
            }
        }

// 判断是单Realm还是多Realm
        if (typeRealms.size() == 1) {
            return doSingleRealmAuthentication(typeRealms.iterator().next(), token);
        } else {
            return doMultiRealmAuthentication(typeRealms, token);
        }
    }

}
