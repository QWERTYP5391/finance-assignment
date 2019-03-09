package com.yahoo.finance.exercise.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SchedulerService {

    private AAPLService aaplService;

    @Value("${fixed-rate.change-in-market}")
    private long fixedRateForChangeInMarket;

    @Value("${fixed-rate.highest-change-in-market}")
    private long fixedRateForHighestChangeInMarket;

    public SchedulerService(AAPLService aaplService) {
        this.aaplService = aaplService;
    }

    @Scheduled(fixedRateString = "${fixed-rate.change-in-market}")
    public void printChangeInMarketCapitalization() {
        printOutput("This is the change in market capitalization: ", aaplService.calculateChangeInMarketCapitalization(), TimeUnit.MILLISECONDS.toSeconds(fixedRateForChangeInMarket));

    }

    @Scheduled(fixedRateString = "${fixed-rate.highest-change-in-market}")
    public void printHighestChangeInMarketCapitalization() {
        printOutput("This is the highest change in market capitalization: ", aaplService.calculateHighestChangeInMarketCapitalization(), TimeUnit.MILLISECONDS.toSeconds(fixedRateForHighestChangeInMarket));

    }

    private void printOutput(String s, double v, long l) {
        log.info(s + v + " after " + l + " seconds");
    }


}
