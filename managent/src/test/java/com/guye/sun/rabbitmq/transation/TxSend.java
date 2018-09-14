package com.guye.sun.rabbitmq.transation;

import com.guye.sun.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by suneee on 2018/8/28.
 */
public class TxSend {

    private static final String QUEUE_NAME = "test_queue_tx";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String msg = "hello mq tx";
        try {
            channel.txSelect();
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            int x = 1/0;
            System.out.println("send tx msg success");
            channel.txCommit();
        } catch (Exception e){
            channel.txRollback();
            System.out.println("send msg txRollback");
        } finally {
            channel.close();
            connection.close();
        }


    }
}
