package com.lqstar.domain;

import com.lqstar.model.User;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/3/14/014 22:17
 */
public interface UserRepository extends BaseRepository<User,Long>{
    User findByName(String name);
}
