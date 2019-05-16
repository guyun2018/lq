package com.lqstar.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/5/8/008 11:29
 */
@Setter
@Getter
public class SysUser2 {
    private String id;
    private String name;
    private String password;
    private String phone;
    private String avatar;
    private List<SysRole> roleList;
}
