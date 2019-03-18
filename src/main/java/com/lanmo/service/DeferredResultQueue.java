package com.lanmo.service;

import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DeferredResultQueue {

    private static Queue<DeferredResult> queue = new ConcurrentLinkedQueue<>();

    public static void save(DeferredResult<Object> deferredResult){
        queue.add(deferredResult);
    }

    public static DeferredResult get(){
       return  queue.poll();
    }

}
