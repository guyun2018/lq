package com.lqstar.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/4/13/013 20:23
 */
@Setter
@Getter
public class Meta {
    private String title;
    private String icon;

    public Meta() {
    }

    public Meta(String title, String icon) {

        this.title = title;
        this.icon = icon;
    }
}
