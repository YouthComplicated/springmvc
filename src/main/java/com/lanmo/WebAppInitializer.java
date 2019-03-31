package com.lanmo;

import com.lanmo.config.AppConfig;
import com.lanmo.config.RootConfig;
import com.lanmo.config.SecurityConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //获取根容器 (spring配置文件) 父容器
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    //获取web容器的配置类(SpringMVC配置文件) 子容器
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfig.class,RootConfig.class};
    }

    //获取DispatchServlet的映射信息
    // 拦截所有请求(/)(包括所有的静态资源 xx.js, xx.png), 但是不包括*.jsp
    // /*: 拦截所有请求 包括*.jsp  jsp页面是tomcat的jsp引擎解析
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * 设定相应的参数：
     *      1、上传路径
     *      2、上传的文件大小(以字节为单位)，不担心多少个part以及每个part的大小。默认没有限制，如果超出限制会发生什么
     *      3、如果到达指定的最大容量，将会写入临时文件路径中。默认值为0，上传所有文件都会写入到磁盘中s
     * @param registration
     */
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(new MultipartConfigElement("C:\\temp\\",
                1*1024*1024, 2*1024*1024,0));

    }
}
