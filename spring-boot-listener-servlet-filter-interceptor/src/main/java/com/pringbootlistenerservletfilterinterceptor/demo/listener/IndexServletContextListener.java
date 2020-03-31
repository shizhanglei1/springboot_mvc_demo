package com.pringbootlistenerservletfilterinterceptor.demo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 配置一个 ServletContext 监听器，使用 @WebListener 标示即可。在 Servlet 容器 初始化 过程中，
 * contextInitialized() 方法会被调用，在容器 销毁 时会调用 contextDestroyed()。
 */
@WebListener
public class IndexServletContextListener implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexServletContextListener.class);
    private static final String Content = "Content created in servlet Context";
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info("START TO INITIAL SERVLET CONTEXT");
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("content",Content);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.info("DESTROY SERVLET CONTEXT");
    }
}
