package com.lqstar;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@ServletComponentScan
@MapperScan(basePackages = {"com.lqstar.mapper"})
public class LqstarApplication {


    public static void main(String[] args) {
        SpringApplication.run(LqstarApplication.class, args);
    }

}
