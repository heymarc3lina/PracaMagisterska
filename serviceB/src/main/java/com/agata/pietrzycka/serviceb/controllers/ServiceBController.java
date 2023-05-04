package com.agata.pietrzycka.serviceb.controllers;

import com.agata.pietrzycka.serviceb.services.ServiceBService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ServiceBController {

    private final ServiceBService serviceBService;

    @GetMapping("/hardTask")
    public List<String> getResultFromEasyTask() {
        return serviceBService.calculateAndGive10RandomSHA();
    }
}
