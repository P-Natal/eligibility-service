package com.natal.eligibilityservice.controller;

import lombok.Data;

@Data
public class ClientEligibityTO {
    private String clientDocument;
    private boolean allow;
    private String reason;
}
