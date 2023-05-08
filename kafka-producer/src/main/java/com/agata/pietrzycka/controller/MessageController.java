package com.agata.pietrzycka.controller;


import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MessageController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/generateTasks")
    public void publish(){
        for(int i = 0 ; i< 100 ; i++){
            kafkaTemplate.send("serviceA", "serviceA task from publish");
            kafkaTemplate.send("serviceB", "hellow");
        }
    }


}
