package com.natal.eligibilityservice.communication;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "subscription", url = "${subscription.host}/subscription")
public interface SubscriptionClient {

    @GetMapping("/{document}")
    SubscriptionClientResponse findClientByDocument(@PathVariable("document") String document);
}
