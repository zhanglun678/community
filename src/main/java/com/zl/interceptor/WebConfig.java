package com.zl.interceptor;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableCaching
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private SessionInterceptor sessionInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册需要拦截的路径 及 排除不需要拦截的路径
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**").excludePathPatterns("/","/callback",
                "/**/*.css",
                "/**/*.js", "/**/*.png", "/**/*.jpg",
                "/**/*.jpeg", "/**/*.gif", "/**/fonts/*");
//        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**");
    }
}
