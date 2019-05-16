package com.lqstar.domain;

import com.lqstar.model.SysUser;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/5/7/007 13:19
 */
public interface SysUserRepository extends BaseRepository<SysUser,String> {
     SysUser findByName(String name);
}
