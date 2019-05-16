package com.lqstar.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/5/7/007 13:20
 */
@Setter
@Getter
@Entity
public class SysUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
    private String password;
    private String phone;
    private String avatar;

    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER,mappedBy = "userId")
    private List<SysUserRole> roles = new ArrayList<>();

//    private List<SysRole> roleList;


}
