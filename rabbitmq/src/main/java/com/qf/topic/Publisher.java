package com.qf.topic;

import com.qf.config.RabbitMQClient;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;

public class Publisher {

    @Test
    public void publish() throws Exception{
        Connection connection = RabbitMQClient.getConnection();

        Channel channel = connection.createChannel();

        channel.exchangeDeclare("topic-exchange", BuiltinExchangeType.TOPIC);
        channel.queueBind("topic-queue-1","topic-exchange","*.red.*");
        channel.queueBind("topic-queue-2","topic-exchange","fast.#");
        channel.queueBind("topic-queue-2","topic-exchange","*.*.rabbit");

        channel.basicPublish("topic-exchange","fast.red.monkey",null,"红快猴子".getBytes());
        channel.basicPublish("topic-exchange","slow.black.dog",null,"黑慢狗".getBytes());
        channel.basicPublish("topic-exchange","fast.white.cat",null,"快白猫".getBytes());
        System.out.println("生产者发布消息成功！");

        channel.close();
        connection.close();
    }
}
