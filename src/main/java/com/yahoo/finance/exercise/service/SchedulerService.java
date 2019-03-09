package com.yahoo.finance.exercise.service;

import com.yahoo.finance.exercise.model.QuoteAggregate;
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
        log.info("This is the current change in market capitalization: {} after {} seconds ", aaplService.calculateChangeInMarketCapitalization(), TimeUnit.MILLISECONDS.toSeconds(fixedRateForChangeInMarket));

    }

    @Scheduled(fixedRateString = "${fixed-rate.highest-change-in-market}")
    public void printHighestChangeInMarketCapitalization() {
        QuoteAggregate highestChangeInMarketCapitalization = aaplService.calculateHighestChangeInMarketCapitalization();
        log.info("This is the highest change in market capitalization: {}, {} after {} seconds ", highestChangeInMarketCapitalization.getHighestChangeInMarketCapitalization(), highestChangeInMarketCapitalization.getLocalDateTime(), TimeUnit.MILLISECONDS.toSeconds(fixedRateForHighestChangeInMarket));
    }
}
