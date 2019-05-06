package com.lqstar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.*;

import static org.springframework.web.cors.CorsConfiguration.ALL;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/3/19/019 14:08
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
//    @Override
//    protected void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("forward:/main");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        super.addViewControllers(registry);
//    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/templates/**")
                .addResourceLocations("classpath:/META-INF/resources/templates/");
    }

}
