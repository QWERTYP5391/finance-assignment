package com.yahoo.finance.exercise.util;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class QuoteUtilityTest {

    @Test
    public void getCalculationOfChangeInMarketCapitalizationWith100and50() {
        double currentValue = 100;
        double lastValue = 50;
        double expected = 100.0;

        assertThat(QuoteUtility.getCalculationOfChangeInMarketCapitalization(currentValue, lastValue), equalTo(expected));
    }

    @Test
    public void getCalculationOfChangeInMarketCapitalizationWith50and100() {
        double currentValue = 50;
        double lastValue = 100;
        double expected = -50.0;

        assertThat(QuoteUtility.getCalculationOfChangeInMarketCapitalization(currentValue, lastValue), equalTo(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCalculationOfChangeInMarketCapitalizationWith50and0() {
        double currentValue = 50;
        double lastValue = 0;

        QuoteUtility.getCalculationOfChangeInMarketCapitalization(currentValue, lastValue);
    }
}