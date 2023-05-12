package com.agata.pietrzycka.controller;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class PublisherMqController {

    private RabbitTemplate rabbitTemplate;

    @GetMapping("/addMessage")
    public String get() {
        for (int i = 0; i < 200; i++) {
            rabbitTemplate.convertAndSend("serviceA", Integer.toString(i));
        }
        return "send";
    }
}
