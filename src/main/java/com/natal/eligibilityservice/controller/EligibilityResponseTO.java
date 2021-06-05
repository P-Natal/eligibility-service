package com.natal.eligibilityservice.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EligibilityResponseTO {

    @JsonProperty("isEligible")
    boolean isEligible;

    public boolean isEligible() {
        return isEligible;
    }
}
