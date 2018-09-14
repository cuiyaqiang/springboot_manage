package com.guye.sun.managent.config.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by suneee on 2018/8/22.
 */
@WebListener
public class ServletListener implements ServletContextListener,ServletContextAttributeListener {
    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {
        System.out.println("servlet新增属性");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent scae) {
        System.out.println("servlet删除属性");
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent scae) {
        System.out.println("servlet修改属性");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("servlet初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("servlet销毁");
    }
}
