package com.example.loadmanagementservice;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DecisionService {
    private final PrometheusService prometheusService;
    private final DockerClientService dockerClientService;
    int newNumberServiceB = 7;
    int newNumberServiceA  = 7;
    private final String SERVICE_B_NAME = "rabbitmq-producer-serviceB";
    private final String SERVICE_A_NAME = "rabbitmq-producer-serviceA";
    private final String IMAGE_B_NAME = "pietrzyckagata/rabbitmq-serviceb:latest";
    private final String IMAGE_A_NAME = "pietrzyckagata/rabbitmq-servicea:latest";

    public DecisionService(PrometheusService prometheusService, DockerClientService dockerClientService) {
        this.prometheusService = prometheusService;
        this.dockerClientService = dockerClientService;
    }

    public void shouldCreateInstance() {
        PrometheusMetrics prometheusMetrics = prometheusService.getMetrics();
        Optional<PrometheusMetricResult> prometheusMetricResultServiceA = prometheusMetrics.getData().getResult().stream().filter(e-> e.getMetric().getJob().equals("serviceA_service")).findFirst();
        Optional<PrometheusMetricResult> prometheusMetricResultServiceB = prometheusMetrics.getData().getResult().stream().filter(e-> e.getMetric().getJob().equals("serviceB_service")).findFirst();
        if(prometheusMetricResultServiceA.isPresent() && prometheusMetricResultServiceB.isPresent()){
            double timeServiceA = Double.valueOf(prometheusMetricResultServiceA.get().getValues().get(0).get(1));
            double timeServiceB = Double.valueOf(prometheusMetricResultServiceB.get().getValues().get(0).get(1));
            if(timeServiceB > timeServiceA){
                if(dockerClientService.canServiceKill(IMAGE_A_NAME)){
                    newNumberServiceB++;
                    dockerClientService.killInstance(IMAGE_A_NAME);
                    dockerClientService.createInstance(newNumberServiceB, SERVICE_B_NAME, IMAGE_B_NAME);
                    System.out.println("ADD B");
                } else {
                    System.out.println("Can't kill serviceA, because there left only 1!");
                }
            }
            if(timeServiceB < timeServiceA){
                if(dockerClientService.canServiceKill(IMAGE_B_NAME)){
                    newNumberServiceA++;
                    dockerClientService.killInstance(IMAGE_B_NAME);
                    dockerClientService.createInstance(newNumberServiceA, SERVICE_A_NAME, IMAGE_A_NAME);
                    System.out.println("ADD A");
                } else {
                    System.out.println("Can't kill serviceB, because there left only 1!");
                }
            }
        }
        dockerClientService.cleanUp();
    }
}
