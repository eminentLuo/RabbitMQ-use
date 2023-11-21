package com.qf.config;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQClient {

    public static Connection getConnection(){

        //创建Connection工厂
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("192.168.2.128");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        //创建Connection
        Connection conn = null;
        try {
            conn = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        //返回
        return conn;
    }
}
