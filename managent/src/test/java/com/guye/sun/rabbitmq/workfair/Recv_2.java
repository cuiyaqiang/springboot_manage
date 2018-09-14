package com.guye.sun.rabbitmq.workfair;

import com.guye.sun.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

/**
 * Created by suneee on 2018/8/27.
 */
public class Recv_2 {

    private static final String QUEUE_NAME="test_work_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.basicQos(1);
        DefaultConsumer consumer = new DefaultConsumer(channel){
            //获取队列中的到达消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println("recv_2 msg:" + msg);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("recv_2 done");
                    //手动回执
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };
        //true 代表自动回执；false 代表手动回执
        boolean autoAck = false;
        //监听队列
        channel.basicConsume(QUEUE_NAME,autoAck,consumer);
    }
}
