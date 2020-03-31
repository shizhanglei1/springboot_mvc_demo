package com.pringbootlistenerservletfilterinterceptor.demo.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.String.format;

/**
 * 配置 IndexHttpServlet，重写 HttpServlet 的 doGet() 方法，直接输出 IndexHttpServlet 定义的
 * 初始化参数 和在 IndexServletContextListener 设置的 ServletContext 上下文参数。
 */
@WebServlet(name = "IndexHttpServlet",displayName = "indexHttpServlet",
            urlPatterns = {"/index/indexHttpServlet"},
            initParams = {@WebInitParam(name="createdBy",value = "Jsper"),
                    @WebInitParam(name = "createdOn",value = "2020-03-30")})
public class IndexHttpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(format("Created by %s", getInitParameter("createdBy")));
        resp.getWriter().println(format("Created on %s", getInitParameter("createdOn")));
        resp.getWriter().println(format("Servlet context param: %s",req.getServletContext().getAttribute("content")));
    }
}
