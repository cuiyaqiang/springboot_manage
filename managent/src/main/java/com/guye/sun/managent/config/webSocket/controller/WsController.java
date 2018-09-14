package com.guye.sun.managent.config.webSocket.controller;

import com.guye.sun.managent.config.webSocket.model.RequestMessage;
import com.guye.sun.managent.config.webSocket.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by suneee on 2018/6/14.
 */
@Controller
public class WsController {

    //通过simpMessagingTemplate向浏览器发送消息
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WsController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/welcome")//当浏览器向服务端发送请求时,通过@MessageMapping映射/welcome这个地址,类似于@ResponseMapping
    @SendTo("/topic/say")//当服务器有消息时,会对订阅了@SendTo中的路径的浏览器发送消息
    public ResponseMessage say(RequestMessage message) {
        System.out.println(message.getName());
        return new ResponseMessage("welcome," + message.getName() + " !");
    }

    /**
     * 定时推送消息
     */
    @Scheduled(fixedRate = 1000)
    public void callback() {
        // 发现消息
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        messagingTemplate.convertAndSend("/topic/callback", "定时推送消息时间: " + df.format(new Date()));
    }

    @MessageMapping("/chat")
    //在springmvc中,可以直接在参数中获得principal,pinciple中包含当前用户信息
    public void handleChat(Principal principal, String msg){
        if ("admin".equals(principal.getName())) {//硬编码,对用户姓名进行判断
            //向用户发送消息,第一个参数:接收消息的用户,第二个参数:浏览器订阅地址,第三个参数:消息
            messagingTemplate.convertAndSendToUser("battcn",
                    "/queue/notifications", principal.getName() + ": " + msg);
            messagingTemplate.convertAndSendToUser("admin",
                    "/queue/notifications", principal.getName() + ": " + msg);
        } else {
            messagingTemplate.convertAndSendToUser("admin",
                    "/queue/notifications", principal.getName() + ": " + msg);
            messagingTemplate.convertAndSendToUser("battcn",
                    "/queue/notifications", principal.getName() + ": " + msg);
        }
    }
}
