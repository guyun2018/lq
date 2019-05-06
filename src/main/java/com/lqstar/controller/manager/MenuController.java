package com.lqstar.controller.manager;

import com.lqstar.model.Menu;
import com.lqstar.model.Meta;
import com.lqstar.model.PageMenu;
import com.lqstar.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/4/14/014 13:27
 */
@Controller
@RequestMapping(value = "/api")
public class MenuController {
    @Autowired
    MenuService menuService;

    @RequestMapping(value = "/getMenuList",method = RequestMethod.POST)
    @ResponseBody
    public List<Menu> getMenuList(){
        return menuService.getMenus();
    }

    @RequestMapping(value = "/getMenu")
    @ResponseBody
    public List<Menu> getMenu(){

//        Menu menu = new Menu();
//        menu.setPath("/personalInfo");
//        menu.setName("PersonalInfo");
//        menu.setComponent("Layout");
//        Meta meta = new Meta();
//        meta.setTitle("个人信息");
//        meta.setIcon("people");
//        menu.setMeta(meta);
//        List<Menu> list = new ArrayList<>();
//        List<Menu> list2 = new ArrayList<>();
//
//        Menu b = new Menu();
//        b.setPath("account");
//        b.setName("Account");
//        b.setComponent("personalInfo/Account");
//        Meta meta2 = new Meta();
//        meta2.setTitle("账号管理");
//        meta2.setIcon("peoples");
//        b.setMeta(meta2);
//
//        Menu b2 = new Menu();
//        b2.setPath("other");
//        b2.setName("Other");
//        b2.setComponent("personalInfo/Other");
//        b2.setMeta(new Meta("其他","eye"));
//        list2.add(b);
//        list2.add(b2);
//        menu.setChildren(list2);
//
//        list.add(menu);
        return menuService.getMenus();
//        return  list;
    }
}
