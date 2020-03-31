package com.pringbootlistenerservletfilterinterceptor.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 在SpringBootApplication上使用@ServletComponentScan注解后，Servlet、Filter、Listener
 * 可以直接通过@WebServlet、@WebFilter、@WebListener注解自动注册，无需其他代码。
 */
@ServletComponentScan //允许扫描和装载当前包路径和子路径下配置的servlet
@EnableWebMvc  //允许spring boot配置spring mvc相关的自定义的属性，比如：拦截器，资源处理器，消息转换
@SpringBootApplication
public class Demo1Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }

}

/***
 *【springboot】之 解析@EnableWebMvc 、WebMvcConfigurationSupport和WebMvcConfigurationAdapter
 * 首先，@EnableWebMvc=WebMvcConfigurationSupport，使用了@EnableWebMvc注解等于扩展了WebMvcConfigurationSupport但是没有重写任何方法
 * 所以有以下几种使用方式：
 * 1、@EnableWebMvc，这种方式会屏蔽springboot的@EnableAutoConfiguration中(既是application中的配置)的设置，完全被spring mvc框架控制。
 *      @Configuration
 *      @EnableWebMvc
 *      @ComponentScan(basePackageClasses = MyConfiguration.class)
 *      public class MyConfiguration {
 *
 *      }
 *
 * 2、extends WebMvcConfigurationSupport，在扩展的类中重写父类的方法即可，这种方式会屏蔽springboot的@EnableAutoConfiguration中的设置
 *
 *      @Configuration
 *  @EnableWebMvc
 *  @ComponentScan(basePackageClasses = MyConfiguration.class)
 *  public class MyConfiguration implements WebMvcConfigurer {
 *
 *            @Override
 *            public void addFormatters(FormatterRegistry formatterRegistry) {
 *          formatterRegistry.addConverter(new MyConverter());
 *            }
 *
 *            @Override
 *            public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
 *          converters.add(new MyHttpMessageConverter());
 *            }
 *
 *  }
 *
 * 3、implements WebMvcConfigurer，在扩展的类中重写父类的方法即可，但是不要加@EnableWebMvc。这种方式依旧使用springboot的@EnableAutoConfiguration中的设置，还可以添加spring mvc框架中的一些配置，比如（过滤器等）
 *  @Configuration
 *  @ComponentScan(basePackageClasses = MyConfiguration.class)
 *  public class MyConfiguration implements WebMvcConfigurer {
 *
 *            @Override
 *            public void addFormatters(FormatterRegistry formatterRegistry) {
 *          formatterRegistry.addConverter(new MyConverter());
 *            }
 *
 *            @Override
 *            public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
 *          converters.add(new MyHttpMessageConverter());
 *            }
 *
 *  }
 *
 */