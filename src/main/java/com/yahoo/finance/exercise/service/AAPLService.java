package com.yahoo.finance.exercise.service;

import com.yahoo.finance.exercise.model.Quote;
import com.yahoo.finance.exercise.model.QuoteAggregate;
import com.yahoo.finance.exercise.util.QuoteUtility;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

/**
 * Service that calculates the overall change in market capitalization  and the highest change in market capitalization
 */
@Service
@Slf4j
public class AAPLService {

    private RestTemplate restTemplate;

    @Setter
    @Getter
    private volatile double mostRecentMarketCapitalization;

    @Setter
    private volatile double highestChangeInMarketCapitalization;

    @Setter
    @Getter
    private volatile double lastMarketCapitalizationRetrievedWithinInterval;

    @Setter
    private volatile double overallChangeInMarketCapitalization;

    @Getter
    @Setter
    private volatile QuoteAggregate aggregateForHighestChangeInMarketCapitalization;

    @Getter
    @Value("${api-endpoint.aapl}")
    private String apiEndpoint;

    public AAPLService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double retrieveChangeInMarketCapitalization() {
        if (lastMarketCapitalizationRetrievedWithinInterval != 0) {
            overallChangeInMarketCapitalization = QuoteUtility.getCalculationOfChangeInMarketCapitalization(mostRecentMarketCapitalization, lastMarketCapitalizationRetrievedWithinInterval);
            lastMarketCapitalizationRetrievedWithinInterval = 0;
        }

        return overallChangeInMarketCapitalization;
    }

    public void calculateHighestChangeInMarketCapitalization() {
        try {
            Quote quote = restTemplate.getForObject(apiEndpoint, Quote.class);

            if (quote != null && quote.getMarketCapitalization() != 0 && mostRecentMarketCapitalization != 0) {
                double calculationOfChangeInMarketCapitalization = QuoteUtility.getCalculationOfChangeInMarketCapitalization(quote.getMarketCapitalization(), mostRecentMarketCapitalization);

                if (Math.abs(calculationOfChangeInMarketCapitalization) > Math.abs(highestChangeInMarketCapitalization)) {
                    highestChangeInMarketCapitalization = calculationOfChangeInMarketCapitalization;
                    aggregateForHighestChangeInMarketCapitalization = new QuoteAggregate(highestChangeInMarketCapitalization, LocalDateTime.now());
                }
            }

            if (quote != null && quote.getMarketCapitalization() != 0) {
                mostRecentMarketCapitalization = quote.getMarketCapitalization();
                if (lastMarketCapitalizationRetrievedWithinInterval == 0) {
                    lastMarketCapitalizationRetrievedWithinInterval = quote.getMarketCapitalization();
                }
            }
        } catch (RestClientException e) {
            log.warn("There was as issue with the given request {}, The issue was caused by {}", apiEndpoint, e.getMessage());
        }

        if (aggregateForHighestChangeInMarketCapitalization == null && highestChangeInMarketCapitalization == 0) {
            aggregateForHighestChangeInMarketCapitalization = new QuoteAggregate(0, LocalDateTime.now());
        }
    }
}
