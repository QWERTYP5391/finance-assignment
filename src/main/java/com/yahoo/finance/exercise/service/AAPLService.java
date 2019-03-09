package com.yahoo.finance.exercise.service;

import com.yahoo.finance.exercise.model.Quote;
import com.yahoo.finance.exercise.util.QuoteUtility;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class AAPLService {

    private RestTemplate restTemplate;

    @Setter
    @Getter
    private double lastMarketCapitalizationForHighestChange;

    @Setter
    private double highestChangeInMarketCapitalization;

    @Setter
    @Getter
    private double lastMarketCapitalization;

    @Setter
    private double lastChangeInMarketCapitalization;

    @Getter
    @Value("${api-endpoint.aapl}")
    private String apiEndpoint;

    public AAPLService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double calculateChangeInMarketCapitalization() {
        try {
            Quote quote = restTemplate.getForObject(apiEndpoint, Quote.class);

            if (quote != null && quote.getMarketCapitalization() != 0 && lastMarketCapitalization != 0) {
                lastChangeInMarketCapitalization = QuoteUtility.getCalculationOfChangeInMarketCapitalization(quote.getMarketCapitalization(), lastMarketCapitalization);
            }

            if (quote != null && quote.getMarketCapitalization() != 0) {
                lastMarketCapitalization = quote.getMarketCapitalization();
            }
        } catch (RestClientException e) {
            log.warn("There was as issue with the given request {}, The issue was caused by {}", apiEndpoint, e.getMessage());
        }

        return lastChangeInMarketCapitalization;
    }

    public double calculateHighestChangeInMarketCapitalization() {
        try {
            Quote quote = restTemplate.getForObject(apiEndpoint, Quote.class);

            if (quote != null && quote.getMarketCapitalization() != 0 && lastMarketCapitalizationForHighestChange != 0) {
                highestChangeInMarketCapitalization = Math.max(highestChangeInMarketCapitalization, QuoteUtility.getCalculationOfChangeInMarketCapitalization(quote.getMarketCapitalization(), lastMarketCapitalizationForHighestChange));
            }

            if (quote != null && quote.getMarketCapitalization() != 0) {
                lastMarketCapitalizationForHighestChange = quote.getMarketCapitalization();
            }
        } catch (RestClientException e) {
            log.warn("There was as issue with the given request {}, The issue was caused by {}", apiEndpoint, e.getMessage());
        }

        return highestChangeInMarketCapitalization;
    }
}
