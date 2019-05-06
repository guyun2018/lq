package com.lqstar.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/3/21/021 22:30
 */
//@Configuration
public class FilterConfig{
    @Bean
    public FilterRegistrationBean registrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new OpenEntityManagerInViewFilter());
        filterRegistrationBean.setName("openEntityManagerInViewFilter");
        filterRegistrationBean.setOrder(Integer.MAX_VALUE);
        return  filterRegistrationBean;
    }
}
