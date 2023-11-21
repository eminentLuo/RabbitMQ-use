package com.qf.work;

import com.qf.config.RabbitMQClient;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;

public class Publisher {

    @Test
    public void publish() throws Exception{
        Connection connection = RabbitMQClient.getConnection();

        Channel channel = connection.createChannel();

        for (int i =0;i<10;i++){
            String msg = "Hello-World!"+i;
            channel.basicPublish("","Work",null,msg.getBytes());

        }

        System.out.println("生产者发布消息成功！");

        channel.close();
        connection.close();
    }
}
