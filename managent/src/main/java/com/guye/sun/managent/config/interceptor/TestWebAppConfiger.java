package com.guye.sun.managent.config.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by suneee on 2018/6/13.
 */
@Configuration
public class TestWebAppConfiger extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new TestInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new TestInterceptor2()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
