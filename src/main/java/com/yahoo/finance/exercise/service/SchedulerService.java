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

    @Value("${fixed-delay.highest-change-in-market}")
    private long fixedDelayForHighestChangeInMarket;

    public SchedulerService(AAPLService aaplService) {
        this.aaplService = aaplService;
    }

    @Scheduled(fixedRateString = "${fixed-rate.change-in-market}")
    public void printChangeInMarketCapitalization() {
        log.info("This is the change in market capitalization: {} for the last {} seconds ", aaplService.calculateChangeInMarketCapitalization(), TimeUnit.MILLISECONDS.toSeconds(fixedRateForChangeInMarket));

        QuoteAggregate aggregateForHighestChangeInMarketCapitalization = aaplService.getAggregateForHighestChangeInMarketCapitalization();

        if (aggregateForHighestChangeInMarketCapitalization != null) {
            log.info("This is the highest change in market capitalization: {}, {}", aggregateForHighestChangeInMarketCapitalization.getHighestChangeInMarketCapitalization(), aggregateForHighestChangeInMarketCapitalization.getLocalDateTime());
        }

    }

    @Scheduled(fixedDelayString = "${fixed-delay.highest-change-in-market}")
    public void pollApi() {
        log.info("Polling the API every {} seconds", TimeUnit.MILLISECONDS.toSeconds(fixedDelayForHighestChangeInMarket));

        aaplService.calculateHighestChangeInMarketCapitalization();
    }
}
