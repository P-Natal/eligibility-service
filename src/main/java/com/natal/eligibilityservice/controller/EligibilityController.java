package com.natal.eligibilityservice.controller;

import com.natal.eligibilityservice.service.EligibilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/eligibility")
public class EligibilityController {

    @Autowired
    private EligibilityService eligibilityService;

    @GetMapping("/{document}")
    public EligibilityResponseTO getEligibility(@PathVariable("document") String document){
        log.info("Recebendo requisição para elegibilidade do cliente com documento {}", document);
        return eligibilityService.getEligibility(document);
    }

    @PostMapping
    public void getEligibility(@RequestBody ClientEligibityTO clientEligibityTO){
        eligibilityService.create(clientEligibityTO);
    }

}
