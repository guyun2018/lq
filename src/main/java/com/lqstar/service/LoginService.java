package com.lqstar.service;

import com.lqstar.model.Role;
import com.lqstar.model.User;

import java.util.Map;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/3/14/014 21:29
 */
public interface LoginService {
    User addUser(Map<String,String> map);
    User findByName(String name);
    Role addRole(Map<String,String> map);
    User findSaltByName(String username);
}
