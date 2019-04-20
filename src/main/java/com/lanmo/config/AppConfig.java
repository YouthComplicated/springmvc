package com.lanmo.config;


import com.lanmo.config.Interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;

import java.io.IOException;

/**
 * SpringMvc只扫描Controller,子容器，禁用默认的过滤规则
 * 该类继承WebMvcConfigurerAdapter指示重写spring mvc 相关功能函数
 */
//@ComponentScan(value = "com.lanmo", includeFilters = {
//        @ComponentScan.Filter(type= FilterType.ANNOTATION, classes={Controller.class})
//},useDefaultFilters = false)
@ComponentScan(value = "com.lanmo", includeFilters = {
        @ComponentScan.Filter(type= FilterType.ANNOTATION, classes={Controller.class})
},useDefaultFilters = false)
@EnableWebMvc //开启spring高级功能 自定义
@EnableTransactionManagement
public class AppConfig extends WebMvcConfigurerAdapter {

    /**
     * 配置jsp视图解析器 html
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

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    @Bean
    public ViewResolver cnViewResolver(ContentNegotiationManager cnm){
        ContentNegotiatingViewResolver cnvr = new ContentNegotiatingViewResolver();
        cnvr.setContentNegotiationManager(cnm);
        return cnvr;
    }

    /**
     * 以bean的方式查找视图
     * @return
     */
    @Bean
    public ViewResolver beanNameViewResolver(){
        return new BeanNameViewResolver();
    }

    /**
     * 将teachers 定义为JSON视图
     * @return
     */
    @Bean
    public View teachers(){
        return new MappingJackson2JsonView();
    }





}
