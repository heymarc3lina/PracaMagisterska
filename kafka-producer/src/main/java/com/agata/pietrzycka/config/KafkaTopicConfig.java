package com.agata.pietrzycka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;


@Configuration
public class KafkaTopicConfig {


   @Bean
    public NewTopic serviceATopic(){
        return TopicBuilder.name("serviceA").build();
   }

    @Bean
    public NewTopic serviceBTopic(){
        return TopicBuilder.name("serviceB").build();
    }
}