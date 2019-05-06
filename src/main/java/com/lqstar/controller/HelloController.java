package com.lqstar.controller;

import com.lqstar.model.Boy;
import com.lqstar.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/3/13/013 20:06
 */
@RestController
public class HelloController {
    @Autowired
    TestService testService;
    @RequestMapping(value = "/hello/{id}",method = RequestMethod.GET)
    public String hello(@PathVariable("id") Integer id){
        System.out.println("AAA");
        return id.toString();
    }
    @GetMapping(value = "/info")
    public Boy getInfo(){
        System.out.println("BBB");
        return testService.getInfo(1);
    }
}
