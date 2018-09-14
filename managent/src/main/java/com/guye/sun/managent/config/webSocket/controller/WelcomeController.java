package com.guye.sun.managent.config.webSocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by suneee on 2018/6/14.
 */
@Controller
public class WelcomeController {

    @RequestMapping(value = "/websocket")
    public String welcome() {
        return "websocket/websocket";
    }

    @RequestMapping(value = "/chat")
    public String chat() {
        return "websocket/chat";
    }
}
