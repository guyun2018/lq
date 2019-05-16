package com.lqstar.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/5/7/007 15:28
 */
@Setter
@Getter
public class SysRole {
    private Integer roleId;
    private String roleName;
    private String roleDesc;
    private List<SysPermission> permissionList;
}
