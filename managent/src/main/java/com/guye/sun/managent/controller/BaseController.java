package com.guye.sun.managent.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by suneee on 2018/5/28.
 */
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(BaseController.class);

    static final String PATH_REDIRECT_LOGOUT = "redirect:/login";
    static final String PATH_LOGIN = "login";
    static final String PATH_MAIN = "main";
    static final String DENIED = "denied";

    @Autowired
    protected HttpServletRequest request;
}
