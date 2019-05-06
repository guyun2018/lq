package com.lqstar.service;

import com.lqstar.domain.RoleRepository;
import com.lqstar.domain.UserRepository;
import com.lqstar.model.Permission;
import com.lqstar.model.Role;
import com.lqstar.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/3/14/014 21:31
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserRepository userRepository;
    RoleRepository roleRepository;

    @Override
    public User addUser(Map<String, String> map) {

        User user = new User();
        user.setName(map.get("username").toString());
        user.setPassword(map.get("password").toString());
        userRepository.save(user);
        return user;
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public Role addRole(Map<String, String> map) {
        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> id = root.get("id");
                Predicate predicate = criteriaBuilder.equal(id, map.get("userId"));
                return predicate;
            }
        };
        Optional<User> user = userRepository.findOne(specification);
        Role role = new Role();
        role.setRoleName(map.get("roleName").toString());
        role.setUser(user.get());
        Permission permission1 = new Permission();
        permission1.setPermission("create");
        permission1.setRole(role);
        Permission permission2 = new Permission();
        permission2.setPermission("update");
        permission2.setRole(role);
        List<Permission> permissions = new ArrayList<Permission>();
        permissions.add(permission1);
        permissions.add(permission2);
        role.setPermissions(permissions);
        roleRepository.save(role);
        return role;
    }

    /**
     * 根据用户名查询salt
     * @param username
     * @return
     */
    @Override
    public User findSaltByName(String username) {
        User user = new User();
        user.setName(username);
        user.setPassword("e18be766ad36f52ea76b2b2c9ad04f8e");
        user.setSalt("abc");
        return user;
    }


}
