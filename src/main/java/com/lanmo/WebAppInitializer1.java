package com.lanmo;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;

//public class WebAppInitializer1 implements WebApplicationInitializer {
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        /**
//         * 上传文件路径配置
//         * 设定相应的参数：
//         *      1、上传路径
//         *      2、上传的文件大小(以字节为单位)，不担心多少个part以及每个part的大小。默认没有限制，如果超出限制会发生什么
//         *      3、如果到达指定的最大容量，将会写入临时文件路径中。默认值为0，上传所有文件都会写入到磁盘中
//         */
//        DispatcherServlet ds = new DispatcherServlet();
//        Registration.Dynamic registration = servletContext.addServlet("uploadServlet", ds);
//        ((ServletRegistration.Dynamic) registration).addMapping("/");
//        ((ServletRegistration.Dynamic) registration).setMultipartConfig(
//                new MultipartConfigElement("c:/temp")
//        );
//    }
//}
