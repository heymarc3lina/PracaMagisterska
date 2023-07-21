package com.example.loadmanagementservice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.Timestamp;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DecisionService {
    private final PrometheusService prometheusService;
    private final DockerClientService dockerClientService;

    public void shouldCreateInstance() {
        PrometheusMetrics prometheusMetrics = prometheusService.getMetrics();
        Optional<PrometheusMetricResult> prometheusMetricResultServiceA = prometheusMetrics.getData().getResult().stream().filter(e-> e.getMetric().getJob().equals("serviceA_service")).findFirst();
        Optional<PrometheusMetricResult> prometheusMetricResultServiceB = prometheusMetrics.getData().getResult().stream().filter(e-> e.getMetric().getJob().equals("serviceB_service")).findFirst();
        if(prometheusMetricResultServiceA.isPresent() && prometheusMetricResultServiceB.isPresent()){
            Double timeServiceA = Double.valueOf(prometheusMetricResultServiceA.get().getValues().get(0).get(1));
            Double timeServiceB = Double.valueOf(prometheusMetricResultServiceB.get().getValues().get(0).get(1));
            if(timeServiceB > timeServiceA){
                if(dockerClientService.canServiceAKill()){
                    dockerClientService.killInstance();
                    dockerClientService.createInstance();
                }
            }
        }



    }
}
