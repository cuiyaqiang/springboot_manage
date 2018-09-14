package com.guye.sun.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by suneee on 2018/8/27.
 */
public class ConnectionUtils {

    /**
     * 获取mq的链接
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        //定义一个链接工厂
        ConnectionFactory factory = new ConnectionFactory();

        //设置服务地址
        factory.setHost("127.0.0.1");
        //设置端口号
        factory.setPort(5672);
        //vhost
        factory.setVirtualHost("/virhost_guye");
        //用户名
        factory.setUsername("guye");
        //密码
        factory.setPassword("1234");
        return factory.newConnection();
    }
}
