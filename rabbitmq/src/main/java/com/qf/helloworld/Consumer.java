package com.qf.helloworld;

import com.qf.config.RabbitMQClient;
import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;

public class Consumer {

    @Test
    public void consume() throws Exception{
        //获取连接对象
        Connection connection = RabbitMQClient.getConnection();
        //创建Channel
        Channel channel = connection.createChannel();

        //声明队列Hello-world
        channel.queueDeclare("HelloWorld",true,false,false,null);

        //开启监听Queue
        DefaultConsumer consume = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("接收到消息："+ new String(body,"UTF-8"));
            }
        };

        channel.basicConsume("HelloWorld",true,consume);

        System.out.println("消费者开始监听队列！");
        System.in.read();
        connection.close();

    }
}
