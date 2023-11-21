package com.qf.pubsub;

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

        channel.exchangeDeclare("pubsub-exchange", BuiltinExchangeType.FANOUT);
        channel.queueBind("pubsub-queue1","pubsub-exchange","");
        channel.queueBind("pubsub-queue2","pubsub-exchange","");
        for (int i =0;i<10;i++){
            String msg = "PubSub!"+i;
            channel.basicPublish("pubsub-exchange","Work",null,msg.getBytes());

        }

        System.out.println("生产者发布消息成功！");

        channel.close();
        connection.close();
    }
}
