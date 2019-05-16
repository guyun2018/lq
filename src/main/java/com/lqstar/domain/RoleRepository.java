package com.lqstar.domain;

import com.lqstar.model.Role;

import java.util.List;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/3/14/014 22:17
 */
public interface RoleRepository extends BaseRepository<Role,Long> {
    Role findById(long id);
    @Override
    List<Role> findAll();
}
