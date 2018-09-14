package com.guye.sun.rabbitmq.confirm;

import com.guye.sun.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by suneee on 2018/8/28.
 */
public class ConfirmSend {

    private static final String QUEUE_NAME = "test_queue_confirm";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //生产者调用confirmSelect将channel设置为confirm模式
        channel.confirmSelect();
        //单条发送
//        String msg = "hello msg confirm";
//        channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
        //批量发送
        for (int i = 0; i < 10; i++){
            String msg = "hello msg confirm " + i;
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
        }
        if (!channel.waitForConfirms()){
            System.out.println("send message failed");
        } else {
            System.out.println("send message success");
        }
        channel.close();
        connection.close();


    }
}
