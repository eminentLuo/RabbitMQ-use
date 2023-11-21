package com.qf.routing;

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

        channel.exchangeDeclare("routing-exchange", BuiltinExchangeType.DIRECT);
        channel.queueBind("routing-queue-error","routing-exchange","ERROR");
        channel.queueBind("routing-queue-info","routing-exchange","INFO");

        channel.basicPublish("routing-exchange","ERROR",null,"ERROR".getBytes());
        channel.basicPublish("routing-exchange","INFO",null,"INFO1".getBytes());
        channel.basicPublish("routing-exchange","INFO",null,"INFO2".getBytes());
        channel.basicPublish("routing-exchange","INFO",null,"INFO3".getBytes());



        System.out.println("生产者发布消息成功！");

        channel.close();
        connection.close();
    }
}
