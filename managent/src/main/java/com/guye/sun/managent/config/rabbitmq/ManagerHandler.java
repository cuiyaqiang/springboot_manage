package com.guye.sun.managent.config.rabbitmq;


import com.guye.sun.managent.pojo.po.Manager;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * MANAGER_QUEUE 消费者
 */
@Component
public class ManagerHandler {

    private static final Logger log = LoggerFactory.getLogger(ManagerHandler.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @RabbitListener(queues = {ManagerRabbitConfig.REGISTER_QUEUE_NAME})
    public void listenerDelayQueue(Manager manager, Message message, Channel channel) {
        log.info("[listenerDelayQueue 监听的消息] - [消费时间] - [{}] - [{}]", LocalDateTime.now(), manager.toString());
        try {
            System.out.println("插入到redis");
            String key = "manager_"+manager.getName();
//            ValueOperations<String,String> operations = redisTemplate.opsForValue();
//            if (redisTemplate.hasKey(key)){
//                redisTemplate.delete(key);
//            }
//            operations.set(key,manager.toString());
            Jedis jedis = new Jedis();
            String value = jedis.get(key);
            if (value != null){
                jedis.del(key);
            }
            jedis.set(key,manager.toString());
            // TODO 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            // TODO 如果报错了,那么我们可以进行容错处理,比如转移当前消息进入其它队列
        }
    }
}
