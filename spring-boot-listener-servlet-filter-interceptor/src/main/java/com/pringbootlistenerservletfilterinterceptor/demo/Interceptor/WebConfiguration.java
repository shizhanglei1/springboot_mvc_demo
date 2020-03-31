package com.pringbootlistenerservletfilterinterceptor.demo.Interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //此注解必须定义，不然interceptor拦截器不会生效
public class WebConfiguration implements WebMvcConfigurer {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebConfiguration.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new FirstIndexInterceptor()).addPathPatterns("/index/**");
        registry.addInterceptor(new SecondIndexInterceptor()).addPathPatterns("/index/**");
        LOGGER.info("Register FirstIndexInterceptor and SecondIndexInterceptor onto InterceptorRegistry");
    }
}
