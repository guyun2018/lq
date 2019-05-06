package com.lqstar.model;

import javax.persistence.*;
import java.util.List;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/3/14/014 20:58
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
    private String password;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Role> roles;
    private String salt;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
