package com.example.lms.component;

import com.example.lms.config.MessageConfig;
import com.example.lms.dto.LoginStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {

    @RabbitListener(queues = MessageConfig.QUEUE)
    public  void consumerMessageFormatQueu(LoginStatus loginStatus){
        System.out.println("Message Received From Queue : " +loginStatus);
    }
}
