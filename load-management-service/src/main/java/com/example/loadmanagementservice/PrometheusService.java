package com.example.loadmanagementservice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@NoArgsConstructor
public class PrometheusService {

    private final String PROMETHEUS_API_URL = "/api/v1/query_range?query=spring_rabbitmq_listener_seconds_sum/spring_rabbitmq_listener_seconds_count";
    private final String PROMETHEUS_API_BASE_URL = "http://localhost:9090";

    public PrometheusMetrics getMetrics() {
        long timeStamp = System.currentTimeMillis() / 1000;
        String url = PROMETHEUS_API_URL + "&start=" + timeStamp + "&end=" + timeStamp + "&step=1";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate
                .getForObject(PROMETHEUS_API_BASE_URL + url, PrometheusMetrics.class);

    }

}