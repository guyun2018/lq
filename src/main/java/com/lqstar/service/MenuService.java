package com.lqstar.service;

import com.lqstar.model.Menu;
import com.lqstar.model.PageMenu;

import java.util.List;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/4/25/025 22:12
 */
public interface MenuService {
    List<PageMenu> getMenuList();
    List<Menu> getMenus();
}
