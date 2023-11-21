package com.qf.work;

import com.qf.config.RabbitMQClient;
import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;

public class Consumer2 {

    @Test
    public void consume() throws Exception{
        //获取连接对象
        Connection connection = RabbitMQClient.getConnection();
        //创建Channel
        final Channel channel = connection.createChannel();

        //声明队列Hello-world
        channel.queueDeclare("Work",true,false,false,null);

        channel.basicQos(1);

        //开启监听Queue
        DefaultConsumer consume = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("消费者2号接收到消息："+ new String(body,"UTF-8"));
                //手动ack
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };

        channel.basicConsume("Work",false,consume);

        System.out.println("消费者开始监听队列！");
        System.in.read();
        connection.close();

    }
}
