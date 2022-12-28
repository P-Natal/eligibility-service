package com.natal.eligibilityservice.controller;

import com.natal.eligibilityservice.service.EligibilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/eligibility")
public class EligibilityController {

    @Autowired
    private EligibilityService eligibilityService;

    @GetMapping("/{document}")
    public ResponseEntity<EligibilityResponseTO> getEligibility(@PathVariable("document") String document){
        if (!CpfValidator.isValid(document)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        log.info("Recebendo requisicao para elegibilidade do cliente com documento {}", document);
        return new ResponseEntity<>(eligibilityService.getEligibility(document), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EligibilityResponseTO> setEligibility(@Valid @RequestBody ClientEligibityTO clientEligibityTO){
        log.info("Recebendo requisicao para criacao de elegibilidade com body {}", clientEligibityTO);
        return new ResponseEntity<>(eligibilityService.create(clientEligibityTO), HttpStatus.OK);
    }

}
