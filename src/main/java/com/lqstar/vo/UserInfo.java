package com.lqstar.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/4/10/010 21:45
 */

@Setter
@Getter
public class UserInfo {

    private String name;

    private String avatar;

    private String introduction;

    private List<?> roles;

}
