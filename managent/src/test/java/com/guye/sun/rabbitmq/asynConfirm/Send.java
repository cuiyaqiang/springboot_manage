package com.guye.sun.rabbitmq.asynConfirm;

import com.guye.sun.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

/**
 * Created by suneee on 2018/8/28.
 */
public class Send {

    private static final String QUEUE_NAME = "test_queue_asyn";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //将channel设置为confirm模式
        channel.confirmSelect();
        //未确认的消息标识
        final SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<>());
        //消息回执的监听
        channel.addConfirmListener(new ConfirmListener() {
            //没有问题的handleAck
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                if (multiple) {
                    System.out.println("---handleAck---multiple");
                    confirmSet.headSet(deliveryTag + 1).clear();
                }else {
                    System.out.println("---handleAck---multiple false");
                    confirmSet.remove(deliveryTag);
                }
            }

            //有问题的handleNack
            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                if (multiple) {
                    System.out.println("---handleNack---multiple");
                    confirmSet.headSet(deliveryTag + 1).clear();
                }else {
                    System.out.println("---handleNack---multiple false");
                    confirmSet.remove(deliveryTag);
                }
            }
        });

        String msg = "sssssssssssssssssss";
        for (int i = 0;i < 5;i++) {
            long seqNo = channel.getNextPublishSeqNo();
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            confirmSet.add(seqNo);
        }
//        while (true){
//        }
    }
}
