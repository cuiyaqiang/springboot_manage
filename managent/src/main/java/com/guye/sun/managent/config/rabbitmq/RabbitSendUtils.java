package com.guye.sun.managent.config.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by suneee on 2018/9/5.
 */
public class RabbitSendUtils {

//    public static void sendMQ() throws IOException, TimeoutException {
//        Connection connection = ConnectionUtils.getConnection();
//        Channel channel = connection.createChannel();
//        channel.exchangeDeclare(EXCHANGE_NAME,"topic");
//        String msg = "商品新增————";
//        channel.basicPublish(EXCHANGE_NAME,"goods.delete",null,msg.getBytes());
//        System.out.println("send---" + msg);
//        channel.close();
//        connection.close();
//    }
}
