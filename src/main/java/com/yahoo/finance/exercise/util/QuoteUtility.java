package com.yahoo.finance.exercise.util;

public class QuoteUtility {

    public static final int PERCENTAGE_TOTAL = 100;

    public static double getCalculationOfChangeInMarketCapitalization(Double currentMarketCapitalization, Double lastMarketCapitalization) {
        return ((Math.abs(lastMarketCapitalization - currentMarketCapitalization)) / lastMarketCapitalization) * PERCENTAGE_TOTAL;
    }
}
