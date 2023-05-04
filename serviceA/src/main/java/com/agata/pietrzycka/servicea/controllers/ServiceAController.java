package com.agata.pietrzycka.servicea.controllers;

import com.agata.pietrzycka.servicea.services.ServiceAService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@AllArgsConstructor
public class ServiceAController {

    private final ServiceAService serviceAService;

    @GetMapping("/easyTask")
    public List<String> getResultFromEasyTask() throws NoSuchAlgorithmException, InvalidKeySpecException {
        return serviceAService.calculateAndGive10RandomSHA();
    }
}
