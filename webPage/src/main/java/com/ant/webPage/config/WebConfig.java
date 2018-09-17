package com.ant.webPage.config;

import com.ant.webPage.interceptor.AuthInterceptor;
import com.ant.webPage.interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * WebMvc配置
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LogInterceptor logInterceptor;
    @Autowired
    private AuthInterceptor authInterceptor;

    // 放行接口
    private final String[] noInterceptor = {
            "/*.html", //html文件
            "/css/**", //css文件夹样式
            "/js/**",
            "/images/**",
            "/login/**", // 用户登录注册相关接口
            "/totices/**",  //公告相关接口
            "/cloud/**",  //云算力列表相关接口
            "/financial/**", //理财列表相关接口
            "/miner/**", //矿机列表相关接口
    };

    /**
     * 重写添加拦截器方法并添加配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor).addPathPatterns("/**");
        registry.addInterceptor(authInterceptor).addPathPatterns("/**").excludePathPatterns(noInterceptor);
    }
}

