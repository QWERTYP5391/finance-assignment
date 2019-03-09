package com.yahoo.finance.exercise.util;

import com.yahoo.finance.exercise.model.Quote;

import java.util.Random;

public class TestUtility {

    public static final String SYMBOL = "AAPL";
    public static final double MAX_MARKET_CAPITALIZATION = 1000000000000d;

    private TestUtility(){

    }

    public static Quote getRandomQuote(){
        Random r = new Random();

        double marketCapitalization = getRandomDouble(r);

        double sharesOutStanding = getRandomDouble(r);

        return new Quote(SYMBOL, marketCapitalization, sharesOutStanding);
    }

    private static double getRandomDouble(Random r) {
        return Double.MIN_VALUE + (MAX_MARKET_CAPITALIZATION - Double.MIN_VALUE) * r.nextDouble();
    }
}
