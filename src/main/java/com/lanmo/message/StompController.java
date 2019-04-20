package com.lanmo.message;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class StompController {

    /**
     * 处理客户端发来的信息
     * @MessageMapping 指定目的地是“/app/marco”
     * （“/app”前缀是隐含的，因为我们将其配置为应用的目的地前缀）。
     * @param shout
     * @return
     */
    @MessageMapping("/marco")
    @SendTo("/topic/marco")
    public Shout stompHandle(Shout shout){
        System.out.println(shout.getMessage());
        String str = "你好啊!";
        Shout s = new Shout();
        s.setMessage("hello!");
        return s;
    }

    /**
     * handle subscriation
     * @return
     */
    @SubscribeMapping("/getShout")
    public Shout getShout(){
        Shout shout = new Shout();
        shout.setMessage("Hello STOMP");
        return shout;
    }
}
