package com.natal.eligibilityservice.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;

@Data
public class ClientEligibityTO {

    @JsonProperty
    @CPF
    private String clientDocument;

    @JsonProperty
    @NotNull
    private boolean allow;

    @JsonProperty
    private String reason;
}
