package com.guye.sun.rabbitmq.topic;

import com.guye.sun.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by suneee on 2018/8/28.
 */
public class Send {


    private static final String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");
//        String msg = "商品新增————";
        List<String> msg = new ArrayList<>();
        for (int i = 0;i < 3;i++){
            msg.add("msg+++++++++++"+i);
        }
        channel.basicPublish(EXCHANGE_NAME,"goods.add",null,msg.toString().getBytes());
        System.out.println("send---" + msg);
        channel.close();
        connection.close();
    }
}
