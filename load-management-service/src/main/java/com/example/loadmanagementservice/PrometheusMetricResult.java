package com.example.loadmanagementservice;

import jakarta.ws.rs.MatrixParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import org.springframework.web.bind.annotation.MatrixVariable;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class PrometheusMetricResult {
    private PrometheusMetric metric;
    private ArrayList<List<String>> values;

}
