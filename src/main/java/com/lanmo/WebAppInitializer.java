package com.lanmo;

import com.lanmo.config.AppConfig;
import com.lanmo.config.RootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //获取根容器 (spring配置文件) 父容器
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    //获取web容器的配置类(SpringMVC配置文件) 子容器
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    //获取DispatchServlet的映射信息
    // 拦截所有请求(/)(包括所有的静态资源 xx.js, xx.png), 但是不包括*.jsp
    // /*: 拦截所有请求 包括*.jsp  jsp页面是tomcat的jsp引擎解析
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
