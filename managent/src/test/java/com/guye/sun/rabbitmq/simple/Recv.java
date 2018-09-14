package com.guye.sun.rabbitmq.simple;

import com.guye.sun.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者获取消息
 * Created by suneee on 2018/8/27.
 */
public class Recv {

    private static final String QUEUE_NAME="test_simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //获取链接
        Connection connection = ConnectionUtils.getConnection();
        //从链接中获取一个通道
        Channel channel = connection.createChannel();
        //创建队列声明
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //声明消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            //获取队列中的到达消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws UnsupportedEncodingException {
                String msg = new String(body, "utf-8");
                System.out.println("recv msg:" + msg);
            }
        };
        //监听队列
        channel.basicConsume(QUEUE_NAME,true,consumer);

        //老版本已过期方法，定义队列的消费者
//        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        //监听队列
//        channel.basicConsume(QUEUE_NAME,true,queueingConsumer);
//        while (true){
//            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
//
//            String msg = new String(delivery.getBody());
//            System.out.println("---recv msg:" + msg);
//
//        }

    }
}
