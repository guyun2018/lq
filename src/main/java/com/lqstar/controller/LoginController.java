package com.lqstar.controller;

import com.alibaba.fastjson.JSON;
import com.lqstar.model.*;
import com.lqstar.service.LoginService;
import com.lqstar.shiro.CustomizedToken;
import com.lqstar.utils.CommonUtil;
import com.lqstar.utils.JwtUtils;
import com.lqstar.utils.RedisUtil;
import com.lqstar.vo.Result;
import com.lqstar.vo.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/3/18/018 20:24
 */
@Controller
@Api(description = "用户接口")
//@CrossOrigin
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    RedisUtil redisUtil;


    public static final long EXPIRE = 20;

    @RequestMapping(value = "/api/login", method = RequestMethod.GET)
    public String login() {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/index";
        }
        return "login";
    }

    //    @CrossOrigin
    @ApiOperation(value = "登录",notes = "登录")
    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody UserBean userBean, HttpSession session, HttpServletResponse response) {
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userBean.getUsername(), userBean.getPassword());
//            CustomizedToken token = new CustomizedToken(userBean.getUsername(), userBean.getPassword(), "myshirorealm");
            subject.login(token);
            User user = (User) subject.getPrincipal();
            session.setAttribute("user", user);
            String salt = CommonUtil.getGUID();
            String newToken = JwtUtils.sign(user.getName(), salt, EXPIRE);
            System.out.println("返回前端token："+newToken);
            response.setHeader("x-auth-token", newToken);
            TokenBean tokenBean = new TokenBean(user.getName(),salt,EXPIRE);
            redisUtil.set(user.getName(), JSON.toJSON(tokenBean), EXPIRE);
            return new Result(true, "登录成功", newToken);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "账号或密码错误！");
        }
    }

    //    @CrossOrigin
    @RequestMapping(value = "/api/info")
    @ResponseBody
    public UserInfo getInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqB8Kia4JicSXtpkiarSNwktPrZgxWCpjhzjV2ggMUpfM9NvnygNuOHhJ3gtLoLY36Ko1wjbpSDTluLA/132");
        userInfo.setIntroduction("cheshi");
        List<Object> list = new ArrayList();
        list.add("admin");
        list.add("dev");
        userInfo.setName("zz");
        userInfo.setRoles(list);
        return userInfo;
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/admin")
    @ResponseBody
    public String admin() {
        return "admin success";
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return "login";
    }

    //    @CrossOrigin
    @RequestMapping(value = "/api/getMenu111")
    @ResponseBody
    public List<Menu> getMenu() {

        Menu menu = new Menu();
        menu.setPath("/r");
        menu.setName("menu");
        menu.setComponent("Layout");
        Meta meta = new Meta();
        meta.setTitle("金融机构");
        meta.setIcon("table");
        menu.setMeta(meta);
        List<Menu> list = new ArrayList<>();
        List<Menu> list2 = new ArrayList<>();

        Menu b = new Menu();
        b.setPath("rr");
        b.setName("menu2");
        b.setComponent("Layout");
        Meta meta2 = new Meta();
        meta2.setTitle("中国银行");
        meta2.setIcon("table");
        b.setMeta(meta2);

        Menu b2 = new Menu();
        b2.setPath("r2");
        b2.setName("r2");
        b2.setComponent("Layout");
        b2.setMeta(new Meta("工商银行", "table"));
        list2.add(b);
        list2.add(b2);
        menu.setChildren(list2);

        list.add(menu);
        return list;
    }

    @RequestMapping(value = "/error", method = RequestMethod.POST)
    @ResponseBody
    public String error() {
        return "error ok!";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public String addUser(@RequestBody UserBean userBean) {
        Map<String, String> map = new HashMap<>();
//        String str = new SecureRandomNumberGenerator().nextBytes().toHex();
//        String password = new Md5Hash("MD5",userBean.getPassword(),"abc",2).toString();
        System.out.println("用户输入密码：" + userBean.getPassword());
        String password = new SimpleHash("MD5", userBean.getPassword(), "abc", 1024).toHex();
        map.put("username", userBean.getUsername());
        map.put("password", password);
        System.out.println(password);
        User user = loginService.addUser(map);
        return "addUser is ok" + user;
    }

    @RequestMapping(value = "/addRole")
    public String addRole(@RequestBody Map<String, String> map) {
        Role role = loginService.addRole(map);
        return "addRole is ok" + role;
    }

    @RequiresRoles("admin")
    @RequiresPermissions("create")
    @RequestMapping(value = "/create")
    public String create() {
        return "Create Ok";
    }

    @RequestMapping(value = "add")
    public String add() {
        return "addUser";
    }
}
