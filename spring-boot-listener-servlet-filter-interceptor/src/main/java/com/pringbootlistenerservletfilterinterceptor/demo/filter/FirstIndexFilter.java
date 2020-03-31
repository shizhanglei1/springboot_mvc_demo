package com.pringbootlistenerservletfilterinterceptor.demo.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;
import static org.springframework.util.StringUtils.isEmpty;

/**
 * 一个 Servlet 请求可以经由多个 Filter 进行过滤，最终由 Servlet 处理并响应客户端。这里配置两个过滤
 * 器示例：
 *
 * 执行顺序控制：通过实践发现如果想要控制filer的执行顺序可以通过控制filter的文件名来控制
 * 比如：
 *     UserLoginFilter.java 和 ApiLog.java 这两个文件里面分别是“用户登录检查过滤器”和“接
 *     口日志过滤器”，因为这两个文件的首字母A排U之前，导致每次执行的时候都是先执行“接口日志
 *     过滤器”再执行“用户登录检查过滤器”
 */
@WebFilter(filterName = "firstIndexFilter",
        displayName = "firstIndexFilter",
        urlPatterns = {"/index/*"},
        initParams = @WebInitParam(
                name = "firstIndexFilterInitParam",
                value = "io.ostenant.springboot.sample.filter.FirstIndexFilter")
)
public class FirstIndexFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(FirstIndexFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("Register a new filter {}", filterConfig.getFilterName());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.info("FirstIndexFilter pre filter the request");
        String filter = servletRequest.getParameter("filter1");
        if(isEmpty(filter)){
            servletResponse.getWriter().println("Filtered by firstIndexFilter, " +
                    "please set request parameter \"filter1\"");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
        LOGGER.info("FirstIndexFilter post filter the response");
    }

    @Override
    public void destroy() {
        LOGGER.info("Destroy filter {}", getClass().getName());
    }
}
