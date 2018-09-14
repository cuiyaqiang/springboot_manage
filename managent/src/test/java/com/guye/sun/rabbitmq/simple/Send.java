package com.guye.sun.rabbitmq.simple;

import com.guye.sun.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 简单队列（一个生产者对应一个消费者）
 * 生产者生产消息
 * Created by suneee on 2018/8/27.
 */
public class Send {

    private static final String QUEUE_NAME="test_simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取一个链接
        Connection connection = ConnectionUtils.getConnection();
        //从链接中获取一个通道
        Channel channel = connection.createChannel();
        //创建队列声明
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        String msg = "hello simple";

        //发送消息
        channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
        System.out.println("---send msg:" + msg);
        //关闭通道
        channel.close();
        //关闭链接
        connection.close();

    }
}
