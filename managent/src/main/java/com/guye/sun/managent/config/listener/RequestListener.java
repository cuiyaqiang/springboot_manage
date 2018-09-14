package com.guye.sun.managent.config.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by suneee on 2018/8/22.
 */
@WebListener
public class RequestListener implements ServletRequestListener,ServletRequestAttributeListener {
    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        System.out.println("request新增属性");
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        System.out.println("request删除属性");
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        System.out.println("request修改属性");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("request销毁");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("request初始化");
    }
}
