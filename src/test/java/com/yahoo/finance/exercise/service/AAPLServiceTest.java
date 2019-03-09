package com.yahoo.finance.exercise.service;

import com.yahoo.finance.exercise.model.Quote;
import com.yahoo.finance.exercise.util.QuoteUtility;
import com.yahoo.finance.exercise.util.TestUtility;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@SpringBootTest
@RunWith(SpringRunner.class)
public class AAPLServiceTest {

    @MockBean
    RestTemplate mockRestTemplate;

    @Autowired
    AAPLService aaplService;

    @Before
    public void setUp() {
        aaplService.setHighestChangeInMarketCapitalization(0.0);
        aaplService.setLastMarketCapitalization(0.0);
        aaplService.setLastChangeInMarketCapitalizationFixedRateMinutesAgo(0.0);
        aaplService.setLastMarketCapitalizationFixedRateMinutesAgo(0.0);
    }

    @Test
    public void testGetChangeInMarketCapitalizationWithInitialRunRandomQuote() {
        Quote randomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(randomQuote);

        double expected = 0.0;

        assertThat(aaplService.getChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalizationFixedRateMinutesAgo(), equalTo(randomQuote.getMarketCapitalization()));
    }

    @Test
    public void testGetChangeInMarketCapitalizationWithInitialRunRandomQuoteProceedingRunRandomQuote() {
        Quote randomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(randomQuote);

        double expected = 0.0;

        assertThat(aaplService.getChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalizationFixedRateMinutesAgo(), equalTo(randomQuote.getMarketCapitalization()));

        Quote secondRandomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(secondRandomQuote);

        expected = QuoteUtility.getCalculationOfChangeInMarketCapitalization(secondRandomQuote.getMarketCapitalization(), randomQuote.getMarketCapitalization());

        assertThat(aaplService.getChangeInMarketCapitalization(), equalTo(expected));
    }

    @Test
    public void testGetChangeInMarketCapitalizationWithInitialRunNullProceedingRunRandomQuote() {

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(null);

        double expected = 0.0;

        assertThat(aaplService.getLastMarketCapitalizationFixedRateMinutesAgo(), equalTo(expected));

        assertThat(aaplService.getChangeInMarketCapitalization(), equalTo(expected));

        Quote secondRandomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(secondRandomQuote);

        assertThat(aaplService.getChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalizationFixedRateMinutesAgo(), equalTo(secondRandomQuote.getMarketCapitalization()));
    }

    @Test
    public void testGetChangeInMarketCapitalizationWithInitialRunZeroQuoteProceedingRunRandomQuote() {

        Quote randomQuote =  new Quote(TestUtility.SYMBOL, 0, 0);

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(randomQuote);

        double expected = 0.0;

        assertThat(aaplService.getLastMarketCapitalizationFixedRateMinutesAgo(), equalTo(randomQuote.getMarketCapitalization()));

        assertThat(aaplService.getChangeInMarketCapitalization(), equalTo(expected));

        Quote secondRandomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(secondRandomQuote);

        assertThat(aaplService.getChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalizationFixedRateMinutesAgo(), equalTo(secondRandomQuote.getMarketCapitalization()));
    }

    @Test
    public void testGetHighestChangeInMarketCapitalizationWithInitialRunRandomQuote() {
        Quote randomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(randomQuote);

        double expected = 0.0;

        assertThat(aaplService.getHighestChangeInMarketCapitalization(), equalTo(expected));
    }

    @Test
    public void testGetHighestChangeInMarketCapitalizationWithInitialRunRandomQuoteProceedingRunRandomQuote() {
        Quote randomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(randomQuote);

        double expected = 0.0;

        assertThat(aaplService.getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalization(), equalTo(randomQuote.getMarketCapitalization()));

        Quote secondRandomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(secondRandomQuote);

        expected = QuoteUtility.getCalculationOfChangeInMarketCapitalization(secondRandomQuote.getMarketCapitalization(), randomQuote.getMarketCapitalization());

        assertThat(aaplService.getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalization(), equalTo(secondRandomQuote.getMarketCapitalization()));
    }

    @Test
    public void testGetHighestChangeInMarketCapitalizationWithInitialRunNullProceedingRunRandomQuote() {

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(null);

        double expected = 0.0;

        assertThat(aaplService.getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalization(), equalTo(expected));

        Quote secondRandomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(secondRandomQuote);

        assertThat(aaplService.getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalization(), equalTo(secondRandomQuote.getMarketCapitalization()));
    }

    @Test
    public void testGetHighestChangeInMarketCapitalizationWithInitialRunZeroQuoteProceedingRunRandomQuote() {

        Quote quote = new Quote(TestUtility.SYMBOL, 0, 0);

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(quote);

        double expected = 0.0;

        assertThat(aaplService.getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalization(), equalTo(expected));

        Quote secondRandomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(secondRandomQuote);

        assertThat(aaplService.getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalization(), equalTo(secondRandomQuote.getMarketCapitalization()));
    }

    @Test
    public void testGetHighestChangeInMarketCapitalizationWithInitialRunRandomQuoteProceedingRunNull() {
        Quote randomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(randomQuote);

        double expected = 0.0;

        assertThat(aaplService.getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalization(), equalTo(randomQuote.getMarketCapitalization()));

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(null);

        assertThat(aaplService.getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalization(), equalTo(randomQuote.getMarketCapitalization()));
    }

    @Test
    public void testGetHighestChangeInMarketCapitalizationWithInitialRunRandomQuoteProceedingRunZeroQuote() {
        Quote randomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(randomQuote);

        double expected = 0.0;

        assertThat(aaplService.getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalization(), equalTo(randomQuote.getMarketCapitalization()));

        Quote secondQuote = new Quote(TestUtility.SYMBOL, 0, 0);

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(secondQuote);

        assertThat(aaplService.getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalization(), equalTo(randomQuote.getMarketCapitalization()));
    }
}