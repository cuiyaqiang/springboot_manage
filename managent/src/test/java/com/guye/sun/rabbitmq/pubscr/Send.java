package com.guye.sun.rabbitmq.pubscr;

import com.guye.sun.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by suneee on 2018/8/27.
 */
public class Send {

    private static final String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) throws IOException, TimeoutException {
        //建立链接
        Connection connection = ConnectionUtils.getConnection();
        //建立通道
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");//分发
        //发送消息
        String msg = "hello pubscr";
        channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes());
        System.out.println("send :" + msg);
        channel.close();
        connection.close();

    }
}
