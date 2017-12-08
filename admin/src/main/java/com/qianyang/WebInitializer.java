package com.qianyang;

import com.qianyang.config.MyBatisConfig;
import com.qianyang.config.SpringMvcConfig;
import com.qianyang.web.fileter.MyFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * 代替 web.xml, 初始化 DispatchServlet
 *
 * 实现 WebApplicationInitializer 的方式不太好区分 spring 容器 和 springmvc 容器
 */
/*public class WebInitializer implements WebApplicationInitializer{
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx
                = new AnnotationConfigWebApplicationContext();
        ctx.register(Config.class);
        ctx.setServletContext(servletContext);

        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher",
                new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);

    }
}*/

/*
 * 可以自己指定 spring 容器和 Spring mvc 容器的配置类
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /*
     * Spring 容器配置类
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{MyBatisConfig.class};
    }

    /*
     * Spring mvc 容器配置类
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /*
     * 添加过滤器
     */
    @Override
    protected Filter[] getServletFilters() {
        //return super.getServletFilters();

        return new Filter[]{new MyFilter()};
    }
}
