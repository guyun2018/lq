package com.lqstar.shiro;

import com.lqstar.model.Permission;
import com.lqstar.model.Role;
import com.lqstar.model.User;
import com.lqstar.service.LoginService;
import com.lqstar.utils.RedisUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/3/14/014 21:24
 */

public class MyShiroRealm extends AuthorizingRealm{
    @Autowired
    LoginService loginService;

    public MyShiroRealm() {
        System.out.println("sansan");
        //因为数据库中的密码做了散列，所以使用shiro的散列Matcher
//        this.setCredentialsMatcher(new HashedCredentialsMatcher(Sha256Hash.ALGORITHM_NAME));
    }
    @Override
    public String getName() {
        return "MyShiroRealm";
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       User user =  (User) principalCollection.getPrimaryPrincipal();
//        User user = (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        User byName = loginService.findByName(user.getName());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        for(Role role: byName.getRoles()){
//            simpleAuthorizationInfo.addRole(role.getRoleName());
//            for(Permission permission: role.getPermissions()){
//                simpleAuthorizationInfo.addStringPermission(permission.getPermission());
//            }
//        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
//        if(authenticationToken.getPrincipal() == null){
//            return null;
//        }
//        User user = loginService.findByName(usernamePasswordToken.getUsername());
        User user = new User();
        user.setName("admin");
        user.setSalt("abc");
        user.setPassword("020202");
        if(user == null){
            throw  new AuthenticationException("用户名或密码错误");
        }
        //输入用户名
//        String name = usernamePasswordToken.getUsername();
//        删String password = new String((char[]) authenticationToken.getCredentials());
//        删String md5 =new Md5Hash(password,name).toHex();

//        if(!JwtUtil.verify(token,username,user.getPassword())){
//            System.out.println("aaa");
//            throw new AuthenticationException("用户名或密码错误");
//        }
            //数据库密码
            String pd = user.getPassword();
            ByteSource bytes = ByteSource.Util.bytes("abc");
            String s = getName();
            System.out.println("数据库密码：:"+pd);

//        return new SimpleAuthenticationInfo(token, token, getName());


            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user,pd,bytes,getName());
            return simpleAuthenticationInfo;
    }
    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

}
