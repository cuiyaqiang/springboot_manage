package com.guye.sun.rabbitmq.workfair;

import com.guye.sun.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 公平分发
 * 工作队列(一个生产者对应多个消费者)
 * Created by suneee on 2018/8/27.
 */
public class Send {

    private static final String QUEUE_NAME="test_work_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        /**
         * 每个消费者发送确认消息之前，消息队列不发送下一个消息到消费者，一次只处理一个消息
         * 限制发送给同一个消费者不得超过一条消息
         */
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);

        for (int i = 0; i < 50; i++){
            String msg = "hello" + i;
            System.out.println("send msg" + msg);
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
//            Thread.sleep(i*20);
        }
        channel.close();
        connection.close();
    }
}
