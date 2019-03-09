package com.yahoo.finance.exercise.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AAPLService {

    private RestTemplate restTemplate;

    private double lastMarketCapitilization;

    private double highestChangeInMarketCapitilization;

    public AAPLService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double getMarketCapitalization () {
        return 0.0;

    }

    public double getHighestChangeInMarketCapitalization () {
        return 0.0;
    }

}
