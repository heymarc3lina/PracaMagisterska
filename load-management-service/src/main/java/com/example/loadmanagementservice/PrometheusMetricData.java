package com.example.loadmanagementservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class PrometheusMetricData {
    private String resultType;
    List<PrometheusMetricResult> result;
}
