package com.natal.eligibilityservice.communication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SubscriptionClientResponse {
    @JsonProperty
    private String name;

    @JsonProperty
    private String document;

    @JsonProperty(required = false)
    private String status;
}
