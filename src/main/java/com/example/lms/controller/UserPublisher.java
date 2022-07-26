package com.example.lms.controller;

import com.example.lms.config.MessageConfig;
import com.example.lms.dto.LoginDTO;
import com.example.lms.dto.LoginStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
//@RequestMapping("/user")
public class UserPublisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/user/{username}")
    public String BookOrder(@RequestBody LoginDTO loginDTO, @PathVariable String username){
//        loginDTO.setId(UUID.randomUUID().toString());
        //restaurant service
        //payment servie
        LoginStatus loginStatus = new LoginStatus(loginDTO, username + " You have been logged in Successfully " );
        /*************** publishing message to exchange associates with routing key ***************/
        template.convertAndSend(MessageConfig.EXCHANGE, MessageConfig.ROUTING_KEY, loginStatus);
        return "Success !!";
    }
}

