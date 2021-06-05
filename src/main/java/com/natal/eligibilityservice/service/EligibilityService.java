package com.natal.eligibilityservice.service;

import com.natal.eligibilityservice.controller.ClientEligibityTO;
import com.natal.eligibilityservice.controller.EligibilityResponseTO;
import org.springframework.stereotype.Service;

@Service
public interface EligibilityService {
    EligibilityResponseTO getEligibility(String document);
    void create(ClientEligibityTO clientEligibityTO);
}
