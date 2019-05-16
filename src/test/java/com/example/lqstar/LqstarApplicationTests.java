package com.example.lqstar;

import com.alibaba.fastjson.JSON;
import com.lqstar.LqstarApplication;
import com.lqstar.domain.RoleRepository;
import com.lqstar.domain.SysUserRepository;
import com.lqstar.domain.UserRepository;
import com.lqstar.mapper.SysUserMapper;
import com.lqstar.model.Role;
import com.lqstar.model.SysUser;
import com.lqstar.model.TokenBean;
import com.lqstar.model.User;
import com.lqstar.utils.RedisConstants;
import com.lqstar.utils.RedisUtil;
import jdk.nashorn.internal.parser.Token;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LqstarApplication.class)
@WebAppConfiguration
@MapperScan(basePackages = {"com.lqstar.mapper"})
public class LqstarApplicationTests {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SysUserRepository sysUserRepository;

    //	@Resource(name = "myRedis")
//	private RedisTemplate<String,Object> redisTemplate;
//	@Test
//	public void contextLoads() {
//		this.redisTemplate.opsForValue().set("study", "java");
//		System.out.println(this.redisTemplate.opsForValue().get("study"));
//	}
    @Test
    public void redisTest1() {
        redisUtil.set("redisTemplate", "这是一条测试数据", RedisConstants.datebase1);
        String redisTemplate = redisUtil.get("redisTemplate", RedisConstants.datebase1).toString();
        System.out.println(redisTemplate);
    }

    @Test
    public void delRedis() {
        redisUtil.del("mykey1");
        System.out.println("删除成功");
    }
    @Test
    public void expireTest(){
        long time = 3600;
       redisUtil.set("abc","aa",time);
        System.out.println(redisUtil.getExpire("abc"));
    }
    @Test
    public void test2(){
        Object admin = redisUtil.get("admin");
        TokenBean token = (TokenBean) admin;
        System.out.println(token);
    }
    @Test
    public void jpaTest(){
        Role byId = roleRepository.findById((long) 1);
        List<Role> all = roleRepository.findAll();
    }
    @Test
    public void test1(){
        SysUser user = sysUserRepository.findByName("兰天球");
    }
    @Autowired
    SysUserMapper sysUserMapper;
    @Test
    public void test3(){
        List<SysUser> sysUsers = sysUserMapper.selectUserList();
    }
}
