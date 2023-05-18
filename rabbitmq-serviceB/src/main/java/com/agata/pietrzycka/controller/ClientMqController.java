package com.agata.pietrzycka.controller;

import com.agata.pietrzycka.services.ServiceBService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ClientMqController {

    private final ServiceBService serviceBService;
    @RabbitListener(queues = "serviceB")
    public void listener(String s){
        int loopNumber = Integer.parseInt(s);
        serviceBService.calculateAndGive10RandomSHA(loopNumber);
        System.out.println(s);
    }
}
