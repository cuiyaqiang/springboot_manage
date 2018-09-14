package com.guye.sun.rabbitmq.asynConfirm;

import com.guye.sun.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

/**
 * Created by suneee on 2018/8/28.
 */
public class Recv {

    private static final String QUEUE_NAME = "test_queue_asyn";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //声明消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            //获取队列中的到达消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws UnsupportedEncodingException {
                String msg = new String(body, "utf-8");
                System.out.println("recv confirm asyn msg:" + msg);
            }
        };
        //监听队列
        channel.basicConsume(QUEUE_NAME,true,consumer);

    }
}
