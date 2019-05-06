package com.lqstar.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import java.util.List;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/4/25/025 21:39
 */
@Getter
@Setter
public class PageMenu {
    private String id;

    private String name;

    private String icon;

    private String url;

    private String pid;

    private Integer orderNumber;

    private List<PageMenu> children;
}
