package com.qf.pubsub;

import com.qf.config.RabbitMQClient;
import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;

public class Consumer1 {

    @Test
    public void consume() throws Exception{
        //获取连接对象
        Connection connection = RabbitMQClient.getConnection();
        //创建Channel
        final Channel channel = connection.createChannel();

        //声明队列Hello-world
        channel.queueDeclare("pubsub-queue1",true,false,false,null);

        //指定当前消费者，一次消费多少个消息
        channel.basicQos(1);

        //开启监听Queue
        DefaultConsumer consume = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1号接收到消息："+ new String(body,"UTF-8"));
                //手动ack
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };

        channel.basicConsume("pubsub-queue1",false,consume);

        System.out.println("消费者开始监听队列！");
        System.in.read();
        connection.close();

    }
}
