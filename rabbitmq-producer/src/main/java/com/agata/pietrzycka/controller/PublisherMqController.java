package com.agata.pietrzycka.controller;

import com.agata.pietrzycka.dto.NumbersToSend;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class PublisherMqController {

    private RabbitTemplate rabbitTemplate;

    @PostMapping("/addMessages")
    public String sendMessageToBothService(@RequestBody NumbersToSend numbersToSend) {
        rabbitTemplate.convertAndSend("serviceA", Integer.toString(numbersToSend.serviceANumber()));
        rabbitTemplate.convertAndSend("serviceB", Integer.toString(numbersToSend.serviceBNumber()));
        return "send";
    }

    @PostMapping("/addMessageToServiceA")
    public String sendMessageToServiceA(@RequestParam int numbersToSend) {
        rabbitTemplate.convertAndSend("serviceA", Integer.toString(numbersToSend));
        return "send";
    }

    @PostMapping("/addMessageToServiceB")
    public String sendMessageToServiceB(@RequestParam int numbersToSend) {
        rabbitTemplate.convertAndSend("serviceB", Integer.toString(numbersToSend));
        return "send";
    }
}
