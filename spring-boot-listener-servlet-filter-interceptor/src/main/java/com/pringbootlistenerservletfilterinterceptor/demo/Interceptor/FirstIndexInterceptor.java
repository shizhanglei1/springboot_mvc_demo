package com.pringbootlistenerservletfilterinterceptor.demo.Interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.util.StringUtils.isEmpty;

/**
 *  拦截器 Interceptor 只对 Handler 生效。Spring MVC 会为 Controller 中的每个请求方法 实例化为一个
 *  Handler对象，由 HandlerMapping 对象路由请求到具体的 Handler，然后由 HandlerAdapter 通过反射
 *  进行请求 处理 和 响应，这中间就穿插着 拦截处理。
 */
public class FirstIndexInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(FirstIndexInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("FirstIndexInterceptor pre intercepted the request");
        String interceptor = request.getParameter("interceptor1");
        if(isEmpty(interceptor)){
            response.getWriter().println("Filtered by FirstIndexFilter, " +
                    "please set request parameter \"interceptor1\"");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOGGER.info("FirstIndexInterceptor post intercepted the response");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.info("FirstIndexInterceptor do something after request completed");
    }
}
