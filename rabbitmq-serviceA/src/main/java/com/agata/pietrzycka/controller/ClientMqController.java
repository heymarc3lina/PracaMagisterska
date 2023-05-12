package com.agata.pietrzycka.controller;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ClientMqController {

    @RabbitListener(queues = "serviceA")
    public void listener(String s){
        System.out.println(s);
    }
}
