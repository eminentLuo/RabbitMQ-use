package com.qf.helloworld;

import com.qf.config.RabbitMQClient;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;

public class Publisher {

    @Test
    public void publish() throws Exception{
        Connection connection = RabbitMQClient.getConnection();

        Channel channel = connection.createChannel();

        String msg = "Hello-World!";
        channel.basicPublish("","HelloWorld",null,msg.getBytes());

        System.out.println("生产者发布消息成功！");

        channel.close();
        connection.close();
    }
}
