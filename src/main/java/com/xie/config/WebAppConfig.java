package com.xie.config;

import com.xie.auth.LoginInteceptor;
import com.xie.csrf.CSRFHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author xie
 * @Date 17/1/20 下午2:44.
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private CSRFHandlerInterceptor csrfHandlerInterceptor;
    @Autowired
    private LoginInteceptor loginInteceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(csrfHandlerInterceptor).addPathPatterns("/**");
        registry.addInterceptor(loginInteceptor).addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/index")
                .excludePathPatterns("/v2/**")
                .excludePathPatterns("/banners/**")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/**/*.html");
        super.addInterceptors(registry);
    }
}
