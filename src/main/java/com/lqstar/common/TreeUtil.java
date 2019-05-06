package com.lqstar.common;

import com.lqstar.model.Menu;
import com.lqstar.model.Meta;
import com.lqstar.model.PageMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/4/25/025 22:25
 */
public class TreeUtil {
    /**
     * 使用递归方法建树
     *
     * @param PageMenus
     * @return
     */
    public static List<PageMenu> buildByRecursive(List<PageMenu> PageMenus) {
        List<PageMenu> trees = new ArrayList<PageMenu>();
        for (PageMenu PageMenu : PageMenus) {
            if (PageMenu.getPid() == null) {
                trees.add(findChildren(PageMenu, PageMenus));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     *
     * @param PageMenus
     * @return
     */
    public static PageMenu findChildren(PageMenu PageMenu, List<PageMenu> PageMenus) {
        PageMenu.setChildren(new ArrayList<PageMenu>());

        for (PageMenu it : PageMenus) {
            if (PageMenu.getId().equals(it.getPid())) {
                if (PageMenu.getChildren() == null) {
                    PageMenu.setChildren(new ArrayList<PageMenu>());
                }
                PageMenu.getChildren().add(findChildren(it, PageMenus));
            }
        }
        return PageMenu;
    }
    public static List<Menu> buildByRecursive2(List<Menu> menus) {
        List<Menu> trees = new ArrayList<Menu>();
        for (Menu menu : menus) {
            if (menu.getPid() == null) {
                trees.add(findChildren2(menu, menus));
            }
            menu.setMeta(new Meta(menu.getTitle(),menu.getIcon()));
        }
        return trees;
    }
    public static Menu findChildren2(Menu menu, List<Menu> menus) {
        menu.setChildren(new ArrayList<Menu>());

        for (Menu it : menus) {
            if (menu.getId().equals(it.getPid())) {
                if (menu.getChildren() == null) {
                    menu.setChildren(new ArrayList<Menu>());
                }
                menu.getChildren().add(findChildren2(it, menus));
            }
                menu.setMeta(new Meta(menu.getTitle(),menu.getIcon()));
        }
        return menu;
    }
}
