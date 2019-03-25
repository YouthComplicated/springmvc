package com.lanmo.config;


import com.lanmo.config.Interceptor.MyInterceptor;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.FileSystemResource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.List;



/**
 * SpringMvc只扫描Controller,子容器，禁用默认的过滤规则
 * 该类继承WebMvcConfigurerAdapter指示重写spring mvc 相关功能函数
 */
@ComponentScan(value = "com.lanmo", includeFilters = {
        @ComponentScan.Filter(type= FilterType.ANNOTATION, classes={Controller.class})
},useDefaultFilters = false)
@EnableWebMvc //开启spring高级功能 自定义
public class AppConfig extends WebMvcConfigurerAdapter {

    /**
     * 配置jsp视图解析器
     * @return
     */
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /**
     * 配置titles解析器
     * @return
     */
    @Bean
    public TilesConfigurer tilesConfigurer(){
        TilesConfigurer ti  = new TilesConfigurer();
        ti.setDefinitions(new String []{
                "/WEB-INF/views/titles.xml"
        });
        ti.setCheckRefresh(true); //启用刷新功能
        return ti;
    }

    /**
     * 配置multipart解析器
     * @return
     */
    @Bean
    public MultipartResolver multipartResolver() throws IOException {
        //非servlet3.0容器
//        return new CommonsMultipartResolver();

//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//        multipartResolver.setUploadTempDir(new FileSystemResource("/tmp/abc"));
//        multipartResolver.setMaxUploadSize(2*1024*1024);
//        multipartResolver.setMaxInMemorySize(0);

        return new StandardServletMultipartResolver();
    }

    //定制环节
    //视图解析器
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
//        super.configureViewResolvers(registry);
        // return this.jsp("/WEB-INF/", ".jsp");
//        registry.jsp();
        registry.jsp("/WEB-INF/views/",".jsp");
    }

    //静态资源访问
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
         registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
    }

//    @Override
//    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//    }
}
