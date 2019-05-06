package com.lqstar.config;

import com.lqstar.shiro.*;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSessionStorageEvaluator;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.*;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/3/14/014 21:46
 */
@Configuration
public class ShiroConfiguration {
//    @Bean
//    public FilterRegistrationBean<Filter> filterRegistrationBean(@Qualifier("securityManager") SecurityManager securityManager) throws Exception {
//        FilterRegistrationBean<Filter> filterRegistration = new FilterRegistrationBean<Filter>();
//        filterRegistration.setFilter((Filter) shiroFilter(securityManager).getObject());
//        System.out.println("o1");
//        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
//        filterRegistration.setAsyncSupported(true);
//        filterRegistration.setEnabled(true);
//        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
//
//        return filterRegistration;
//    }

    @Bean("securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置认证器
//        securityManager.setAuthenticator(customModularRealmAuthenticator());
        List<Realm> realms = new ArrayList<>();
        realms.add(myShiroRealm());
        realms.add(jwtShiroRealm());
        securityManager.setRealms(realms);
        return securityManager;
    }

    /**
     *      * 系统自带的Realm管理，主要针对多realm
     *     
     */
    @Bean
    public CustomModularRealmAuthenticator customModularRealmAuthenticator() {
        CustomModularRealmAuthenticator customModularRealmAuthenticator = new CustomModularRealmAuthenticator();
        customModularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return customModularRealmAuthenticator;
    }

    /**
     * 初始化Authenticator
     */
    @Bean
    public Authenticator authenticator() {
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
//        设置两个Realm，一个用于用户登录验证和访问权限获取；一个用于jwt token的认证
//        authenticator.setRealms(Arrays.asList(jwtShiroRealm(), myShiroRealm()));
//        设置多个realm认证策略，一个成功即跳过其它的
        authenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
        return authenticator;
    }
    @Bean("myShiroRealm")
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setAuthorizationCachingEnabled(false);
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    /**
     * 用于JWT token认证的realm
     */
    @Bean("jwtRealm")
    public JWTShiroRealm jwtShiroRealm() {
        JWTShiroRealm jwtShiroRealm = new JWTShiroRealm();
        return jwtShiroRealm;
    }
    /**
     * 禁用session, 不保存用户登录状态。保证每次请求都重新认证。
     * 需要注意的是，如果用户代码里调用Subject.getSession()还是可以用session，如果要完全禁用，要配合下面的noSessionCreation的Filter来实现
     */
    @Bean
    protected SessionStorageEvaluator sessionStorageEvaluator() {
        DefaultWebSessionStorageEvaluator sessionStorageEvaluator = new DefaultWebSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        return sessionStorageEvaluator;
    }
    /**
     * 设置过滤器，将自定义的Filter加入
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filterMap = new HashMap<String, Filter>(1);
        filterMap.put("authcToken", createAuthFilter());
        filterMap.put("anyRole", createRolesFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        Map<String, String> map = new HashMap<>();
        map.put("/logout", "anon");
        map.put("/login", "anon");
        map.put("/static/**", "anon");
        map.put("/api/login","anon");
//        map.put("/api/info","anon");
        map.put("/api/logout","authcToken[permissive]");
        map.put("/api/account","anyRole[admin,manager]");
        map.put("/api/**", "authcToken");
        map.put("/add", "anon");
        map.put("/menu/**", "anon");
        map.put("/admin", "roles[admin]");
        map.put("/update", "perms[edit]");
        map.put("/addUser", "anon");
        map.put("/druid/**", "anon");
        map.put("/swagger-ui.html","anon");

        map.put("/**", "authc");
//        shiroFilterFactoryBean.setLoginUrl("/login");
//        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    protected JwtAuthFilter createAuthFilter() {
        return new JwtAuthFilter();
    }

    protected AnyRolesAuthorizationFilter createRolesFilter() {
        return new AnyRolesAuthorizationFilter();
    }
}
