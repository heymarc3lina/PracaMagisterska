package com.agata.pietrzycka.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    @Bean
    public Queue queueServiceA(){
        return new Queue("serviceA");
    }

    @Bean
    public Queue queueServiceB(){
        return new Queue("serviceB");
    }
}
