package com.chris.pethospital.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created by Chris on 2017-07-31.
 */
@Configuration
public class WebMvcConf implements WebMvcConfigurer {
    @Autowired
    private UserSecurityInterceptor securityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(securityInterceptor).addPathPatterns("/admin/**");//配置登录拦截器拦截路径
        InterceptorRegistration ir = registry.addInterceptor(securityInterceptor);
        ir.addPathPatterns("/admin/**");
    }
}
