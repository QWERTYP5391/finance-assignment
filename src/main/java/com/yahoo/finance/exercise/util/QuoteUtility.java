package com.yahoo.finance.exercise.util;

public class QuoteUtility {

    public static final int PERCENTAGE_TOTAL = 100;

    public static double getCalculationOfChangeInMarketCapitalization(double currentMarketCapitalization, double lastMarketCapitalization) {
        if (lastMarketCapitalization == 0) {
            throw new IllegalArgumentException("You cannot divide a number by zero");
        }

        double difference = currentMarketCapitalization - lastMarketCapitalization;

        return Math.signum(difference) * ((Math.abs(difference)) / lastMarketCapitalization) * PERCENTAGE_TOTAL;
    }
}
