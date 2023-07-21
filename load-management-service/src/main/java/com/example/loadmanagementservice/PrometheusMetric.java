package com.example.loadmanagementservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class PrometheusMetric {
    private String exception;
    private String instance;
    private String job;
    private String listener_id;
    private String queue;
    private String result;

}
