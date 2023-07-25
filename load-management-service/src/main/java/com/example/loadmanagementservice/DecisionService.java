package com.example.loadmanagementservice;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DecisionService {
    private final PrometheusService prometheusService;
    private final DockerClientService dockerClientService;
    int newNumber = 4;

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
                if(dockerClientService.canServiceAKill()){
                    newNumber++;
                    dockerClientService.killInstance();
                    dockerClientService.createInstance(newNumber);
                }
            }
        }



    }
}
