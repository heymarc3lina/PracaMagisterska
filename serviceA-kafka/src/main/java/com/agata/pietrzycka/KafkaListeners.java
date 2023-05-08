package com.agata.pietrzycka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "serviceA", groupId = "groupId")
    void listener(String data){
        System.out.println("Listner recived "+ data);
    }
}
