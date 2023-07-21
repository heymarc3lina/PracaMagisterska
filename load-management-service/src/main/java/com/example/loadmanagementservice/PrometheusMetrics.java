package com.example.loadmanagementservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class PrometheusMetrics {
    private String status;
    private PrometheusMetricData data;


}


