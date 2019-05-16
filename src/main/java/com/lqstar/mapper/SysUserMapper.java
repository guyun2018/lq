package com.lqstar.mapper;

import com.lqstar.model.SysUser;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/5/8/008 11:15
 */
@Component
public interface SysUserMapper {
    List<SysUser> selectUserList();
}
