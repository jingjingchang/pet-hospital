package com.zcmzjp.wx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Chris on 2017-07-31.
 */
@Configuration
public class WebMvcConf extends WebMvcConfigurerAdapter {
    @Autowired
    private UserSecurityInterceptor securityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(securityInterceptor).addPathPatterns("/admin/**");//配置登录拦截器拦截路径
        InterceptorRegistration ir = registry.addInterceptor(securityInterceptor);
        ir.addPathPatterns("/admin/**");
        ir.excludePathPatterns("/com/zcmzjp/wx/**");
    }
}
