package com.lqstar.common;

import com.lqstar.model.User;
import com.lqstar.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/4/29/029 10:01
 */
public class TokenService {

//    private static final String encryptSalt = "F12839WhsnnEV$#23b";
//
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    /**
//     * 保存user登录信息，返回token
//     * @param userDto
//     */
//    public String generateJwtToken(String username) {
//        String salt = "12345";//JwtUtils.generateSalt();
//        /**
//         * @todo 将salt保存到数据库或者缓存中
//         * redisTemplate.opsForValue().set("token:"+username, salt, 3600, TimeUnit.SECONDS);
//         */
//        return JwtUtils.sign(username, salt, 3600); //生成jwt token，设置过期时间为1小时
//    }
//
//    /**
//     * 获取上次token生成时的salt值和登录用户信息
//     * @param username
//     * @return
//     */
//    public User getJwtTokenInfo(String username) {
//        String salt = "12345";
//        /**
//         * @todo 从数据库或者缓存中取出jwt token生成时用的salt
//         * salt = redisTemplate.opsForValue().get("token:"+username);
//         */
//        User user = getUserInfo(username);
//        user.setSalt(salt);
//        return user;
//    }
//
//    /**
//     * 清除token信息
//     * @param userName 登录用户名
//     * @param terminal 登录终端
//     */
//    public void deleteLoginInfo(String username) {
//        /**
//         * @todo 删除数据库或者缓存中保存的salt
//         * redisTemplate.delete("token:"+username);
//         */
//
//    }
//
//    /**
//     * 获取数据库中保存的用户信息，主要是加密后的密码
//     * @param userName
//     * @return
//     */
//    public User getUserInfo(String userName) {
//        User user = new User();
//        user.setUserId(1L);
//        user.setUsername("admin");
//        user.setEncryptPwd(new Sha256Hash("123456", encryptSalt).toHex());
//        return user;
//    }
//
//    /**
//     * 获取用户角色列表，强烈建议从缓存中获取
//     * @param userId
//     * @return
//     */
//    public List<String> getUserRoles(Long userId){
//        return Arrays.asList("admin");
//    }
}
