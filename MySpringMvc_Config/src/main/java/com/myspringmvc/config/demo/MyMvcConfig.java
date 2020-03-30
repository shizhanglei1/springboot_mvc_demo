package com.myspringmvc.config.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 在spring boot1.0+，我们可以使用WebMvcConfigurerAdapter来扩展springMVC的功能，其中自定义的拦截器并不会拦截
 * 静态资源（js、css等）。WebMvcConfigurerAdapter此方法已不推荐使用
 *
 * 在spring boot2.0+以后，WebMvcConfigurerAdapter就过时了，目前通过继承WebMvcConfigurationSupport类
 * （ps：继承后好像MVC自动配置不生效了）或者实现WebMvcConfigurer接口来扩展springMVC的功能。然而该版本自定义
 * 的拦截器会拦截静态资源，因此在使用spring2.0+时，配置拦截器之后，我们要把静态资源的路径加入到不拦截的路径之中。
 *
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    private class LoginHandlerInterceptor implements HandlerInterceptor {
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //将静态资源排除在拦截之外
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index.html","/","/user/login","/static/**");

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login.html");
        registry.addViewController("/index").setViewName("logon.html");
    }


}
