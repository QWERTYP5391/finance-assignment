package com.yahoo.finance.exercise.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Quote {

    private String symbol;

    @JsonProperty("regularMarketPrice")
    private double regularMarketPrice;

    @JsonProperty("sharesOutstanding")
    private double sharesOutstanding;

    public double getMarketCapitalization(){
        return regularMarketPrice * sharesOutstanding;
    }

    public Quote() {
    }

    public Quote(String symbol, double regularMarketPrice, double sharesOutstanding) {
        this.symbol = symbol;
        this.regularMarketPrice = regularMarketPrice;
        this.sharesOutstanding = sharesOutstanding;
    }
}
