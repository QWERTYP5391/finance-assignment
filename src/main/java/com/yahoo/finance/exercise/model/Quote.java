package com.yahoo.finance.exercise.model;

import lombok.Data;

@Data
public class Quote {

    private String symbol;

    private double regularMarketPrice;

    private double sharesOutstanding;

    public double getMarketCapitilization(){
        return regularMarketPrice * sharesOutstanding;
    }


}
