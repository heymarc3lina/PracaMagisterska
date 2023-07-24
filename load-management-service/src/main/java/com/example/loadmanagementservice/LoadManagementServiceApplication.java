package com.example.loadmanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoadManagementServiceApplication {
    public static  DecisionService decisionService;


    public  LoadManagementServiceApplication(){
        decisionService = new DecisionService(new PrometheusService(), new DockerClientService());
    }

    public static void main(String[] args) {
        SpringApplication.run(LoadManagementServiceApplication.class, args);
        int newNumber = 7;
        while(true){
            decisionService.shouldCreateInstance(newNumber);
            newNumber++;
        }
    }

}
