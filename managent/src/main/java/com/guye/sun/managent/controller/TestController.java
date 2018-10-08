package com.guye.sun.managent.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.guye.sun.dubbo.api.TestProvider;
import com.guye.sun.dubbo.pojo.dto.ManagerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by suneee on 2018/10/8.
 */
@RestController
public class TestController {

    @Reference
    private TestProvider testProvider;

    @GetMapping(value = "/testDubbo")
    public ManagerDto testDubbo() {
        return testProvider.selectManagerByAccount("admin");
    }
}
