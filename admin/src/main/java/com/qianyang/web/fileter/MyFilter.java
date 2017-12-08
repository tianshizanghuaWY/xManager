package com.qianyang.web.fileter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * 加这个 filter 是测试这个filter 对 app 的 UserController 是否起作用
 * 测试结果：起作用
 *
 * 因为 app.controller  / web.controller 都是被 SpringMvcConfig 扫描装载进 IOC
 */
public class MyFilter implements Filter{
    private static Logger logger = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        logger.info("---------------------> my filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        logger.info("---------------------> my filter doFilter");

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

        logger.info("---------------------> my filter destroy");
    }
}
