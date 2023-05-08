package com.agata.pietrzycka;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate){
//        return args -> {
//            for(int i = 0 ; i< 100 ; i++){
//                kafkaTemplate.send("serviceA", "serviceA task");
//                kafkaTemplate.send("serviceB", "hellow");
//            }
//
//        };
//    }

}
