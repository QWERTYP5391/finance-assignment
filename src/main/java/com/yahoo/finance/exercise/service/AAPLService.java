package com.yahoo.finance.exercise.service;

import com.yahoo.finance.exercise.model.Quote;
import com.yahoo.finance.exercise.util.QuoteUtility;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class AAPLService {

    private RestTemplate restTemplate;

    @Setter
    @Getter
    private double lastMarketCapitalization;

    @Setter
    private double highestChangeInMarketCapitalization;

    @Setter
    @Getter
    private double lastMarketCapitalizationFixedRateMinutesAgo;

    @Setter
    private double lastChangeInMarketCapitalizationFixedRateMinutesAgo;

    @Getter
    @Value("${api-endpoint.aapl}")
    private String apiEndpoint;

    public AAPLService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double getChangeInMarketCapitalization() {

        Quote quote = restTemplate.getForObject(apiEndpoint, Quote.class);

        if (quote != null && quote.getMarketCapitalization() != 0 && lastMarketCapitalizationFixedRateMinutesAgo != 0) {
            lastChangeInMarketCapitalizationFixedRateMinutesAgo = QuoteUtility.getCalculationOfChangeInMarketCapitalization(quote.getMarketCapitalization(), lastMarketCapitalizationFixedRateMinutesAgo);
        }

        if(quote != null && quote.getMarketCapitalization() != 0){
            lastMarketCapitalizationFixedRateMinutesAgo = quote.getMarketCapitalization();
        }

        return lastChangeInMarketCapitalizationFixedRateMinutesAgo;
    }

    public double getHighestChangeInMarketCapitalization() {
        Quote quote = restTemplate.getForObject(apiEndpoint, Quote.class);

        if (quote != null && quote.getMarketCapitalization() != 0 && lastMarketCapitalization != 0) {
            highestChangeInMarketCapitalization = Math.max(highestChangeInMarketCapitalization, QuoteUtility.getCalculationOfChangeInMarketCapitalization(quote.getMarketCapitalization(), lastMarketCapitalization));
        }

        if (quote != null && quote.getMarketCapitalization() != 0) {
            lastMarketCapitalization = quote.getMarketCapitalization();
        }

        return highestChangeInMarketCapitalization;
    }
}
