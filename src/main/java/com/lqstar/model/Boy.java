package com.lqstar.model;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/3/13/013 21:58
 */
public class Boy {
    private Integer id;
    private String username;
    private Integer age;

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
