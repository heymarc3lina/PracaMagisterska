package com.agata.pietrzycka.controller;

import com.agata.pietrzycka.services.ServiceAService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ClientMqController {

    private final ServiceAService serviceAService;

    @RabbitListener(queues = "serviceA")
    public void listener(String s){
        int loopNumber = Integer.parseInt(s);
        serviceAService.calculateAndGive10RandomSHA(loopNumber);
        System.out.println(s);
    }
}
