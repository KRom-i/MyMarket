package com.gb.market.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SenderApp {

    private final static String TASK_QUEUE_NAME = "task_queue";
    @Value("${data.bus.host}")
    private static String host;

    public void send(String message){

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);

        try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
            boolean durable = true;
            channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, null);

            channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_BASIC,
                    message.getBytes("UTF-8"));

            System.out.println("Send message: " + message);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
