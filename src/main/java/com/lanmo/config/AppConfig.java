package com.lanmo.config;


import com.lanmo.config.Interceptor.MyInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

//SpringMvc只扫描Controller,子容器，禁用默认的过滤规则
@ComponentScan(value = "com.lanmo", includeFilters = {
        @ComponentScan.Filter(type= FilterType.ANNOTATION, classes={Controller.class})
},useDefaultFilters = false)
@EnableWebMvc //开启spring高级功能 自定义
public class AppConfig extends WebMvcConfigurerAdapter {

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
