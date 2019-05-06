package com.lqstar.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.lqstar.common.TreeUtil;
import com.lqstar.mapper.MenuMapper;
import com.lqstar.model.Menu;
import com.lqstar.model.Meta;
import com.lqstar.model.PageMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/4/25/025 22:28
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuMapper menuMapper;


    @Override
    public List<PageMenu> getMenuList() {
        List<PageMenu> pageMenus = menuMapper.selectMenuList();
        List<PageMenu> menuTree = TreeUtil.buildByRecursive(pageMenus);
        return menuTree;
    }

    @Override
    public List<Menu> getMenus() {
        List<Menu> menus = menuMapper.selectMenus();
        List<Menu> menus1 = TreeUtil.buildByRecursive2(menus);
//        for(Menu menu: menus1){
//            menu.setMeta(new Meta(menu.getTitle(),menu.getIcon()));
//        }
        return menus1;
    }
}
