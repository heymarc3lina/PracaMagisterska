package com.agata.pietrzycka.servicea.controllers;

import com.agata.pietrzycka.servicea.services.ServiceAService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ServiceAController {

    private final ServiceAService serviceAService;

    @GetMapping("/easyTask")
    public List<String> getResultFromEasyTask(@RequestParam int loopQuantity) {
        return serviceAService.calculateAndGive10RandomSHA(loopQuantity);
    }
}
