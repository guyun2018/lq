package com.lqstar.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/5/5/005 15:54
 */
@Setter
@Getter
public class TokenBean implements Serializable{
    private static final long serialVersionUID = -5976810446524501588L;
    private String username;
    private String salt;

    public TokenBean() {
    }

    public TokenBean(String username, String salt, long expire) {
        this.username = username;
        this.salt = salt;
        this.expire = expire;
    }

    private long expire;
}
