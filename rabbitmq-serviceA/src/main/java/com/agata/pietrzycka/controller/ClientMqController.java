package com.agata.pietrzycka.controller;

import com.agata.pietrzycka.services.ServiceAService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ClientMqController {

    private final ServiceAService serviceAService;
    private final Timer messageProcessingTimer;

    public MessageListener(MeterRegistry registry) {
        // Inicjalizacja Timera do monitorowania czasu przetwarzania wiadomości
        messageProcessingTimer = Timer.builder("message.processing.time")
                .description("Czas przetwarzania wiadomości z RabbitMQ")
                .publishPercentiles(0.99) // Dodanie 99 percentyla do timera
                .register(registry);
    }

    @RabbitListener(queues = "serviceA")
    public void listener(String s){
        Timer.Sample sample = Timer.start();
        int loopNumber = Integer.parseInt(s);
        serviceAService.calculateAndGive10RandomSHA(loopNumber);
        System.out.println(s);
        sample.stop(messageProcessingTimer);
    }
}
