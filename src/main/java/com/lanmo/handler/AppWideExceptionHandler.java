package com.lanmo.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 使用通知拦截异常
 */
@ControllerAdvice
public class AppWideExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String handException(HttpServletRequest request, HttpServletResponse response, Exception ex){
        System.out.println(ex);
        return "aaa";
    }
}
