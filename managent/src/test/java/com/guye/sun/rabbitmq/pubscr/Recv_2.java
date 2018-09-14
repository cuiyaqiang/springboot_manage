package com.guye.sun.rabbitmq.pubscr;

import com.guye.sun.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by suneee on 2018/8/27.
 */
public class Recv_2 {

    private static final String QUEUE_NAME = "test_queue_fanout_ams";
    private static final String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) throws IOException, TimeoutException {
        //建立链接
        Connection connection = ConnectionUtils.getConnection();
        //建立通道
        final Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //绑定交换机到队列
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");
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
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };
        boolean autoAck = false;//true为自动应答，false为手动应答
        //监听队列
        channel.basicConsume(QUEUE_NAME,autoAck,consumer);


    }
}
