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
            "/*.html",
            "/css/**",
            "/js/**",
            "/images/**",
            "/user/login", // 用户登录
            "/user/logout", // 管理员登录
            "/totices/findList",  //公告列表
            "/cloud/findList",  //云算力列表
            "/cloud/findFirst",  //云算力排序第一条
            "/cloud/findOne/**",
            "/financial/findList", //理财列表
            "/financial/findFour", //理财列表前四条
            "/financial/findOne",
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

