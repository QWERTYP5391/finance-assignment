package com.yahoo.finance.exercise.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Quote {

    @NonNull private String symbol;

    @NonNull private double regularMarketPrice;

    @NonNull private double sharesOutstanding;

    public double getMarketCapitalization(){
        return regularMarketPrice * sharesOutstanding;
    }


}
