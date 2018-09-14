package com.guye.sun.managent.config.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by suneee on 2018/8/22.
 */
@WebListener
public class SessionListener implements HttpSessionListener,HttpSessionAttributeListener {

    public int count = 0;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        count++;
        se.getSession().setAttribute("count",count);
        System.out.println("session创建："+count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        count--;
        se.getSession().setAttribute("count",count);
        System.out.println("session销毁："+count);
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        System.out.println("session新增了一个属性");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        System.out.println("session删除了一个属性");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        System.out.println("session修改了一个属性");
    }
}
