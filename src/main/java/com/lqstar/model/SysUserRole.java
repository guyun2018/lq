package com.lqstar.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/5/7/007 13:36
 */
@Getter
@Setter
@Entity
public class SysUserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String userId;
    private Integer roleId;
}
