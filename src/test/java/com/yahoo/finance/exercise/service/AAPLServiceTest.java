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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@SpringBootTest
@RunWith(SpringRunner.class)
public class AAPLServiceTest {

    private static final String EXCEPTION = "This is a rest client exception";

    @MockBean
    RestTemplate mockRestTemplate;

    @MockBean
    SchedulerService schedulerService;

    @Autowired
    AAPLService aaplService;

    @Before
    public void setUp() {
        aaplService.setHighestChangeInMarketCapitalization(0.0);
        aaplService.setMostRecentMarketCapitalization(0.0);
        aaplService.setOverallChangeInMarketCapitalization(0.0);
        aaplService.setLastMarketCapitalizationRetrievedWithinInterval(0.0);
        aaplService.setAggregateForHighestChangeInMarketCapitalization(null);
    }

    @Test
    public void testCalculateChangeInMarketCapitalizationWithInitialRunRandomQuote() {
        Quote randomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(randomQuote);

        aaplService.calculateHighestChangeInMarketCapitalization();

        double expected = 0.0;

        assertThat(aaplService.retrieveChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalizationRetrievedWithinInterval(), equalTo(randomQuote.getMarketCapitalization()));
    }

    @Test
    public void testCalculateChangeInMarketCapitalizationWithInitialRunRandomQuoteProceedingRunRandomQuote() {
        Quote randomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(randomQuote);

        aaplService.calculateHighestChangeInMarketCapitalization();

        double expected = 0.0;

        assertThat(aaplService.retrieveChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalizationRetrievedWithinInterval(), equalTo(randomQuote.getMarketCapitalization()));

        Quote secondRandomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(secondRandomQuote);

        aaplService.calculateHighestChangeInMarketCapitalization();

        expected = QuoteUtility.getCalculationOfChangeInMarketCapitalization(secondRandomQuote.getMarketCapitalization(), randomQuote.getMarketCapitalization());

        assertThat(aaplService.retrieveChangeInMarketCapitalization(), equalTo(expected));
    }

    @Test
    public void testCalculateChangeInMarketCapitalizationWithInitialRunNullProceedingRunRandomQuote() {

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(null);

        aaplService.calculateHighestChangeInMarketCapitalization();

        double expected = 0.0;

        assertThat(aaplService.getLastMarketCapitalizationRetrievedWithinInterval(), equalTo(expected));

        assertThat(aaplService.retrieveChangeInMarketCapitalization(), equalTo(expected));

        Quote secondRandomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(secondRandomQuote);

        aaplService.calculateHighestChangeInMarketCapitalization();

        assertThat(aaplService.retrieveChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalizationRetrievedWithinInterval(), equalTo(secondRandomQuote.getMarketCapitalization()));
    }

    @Test
    public void testCalculateChangeInMarketCapitalizationWithInitialRunZeroQuoteProceedingRunRandomQuote() {

        Quote quote = new Quote(TestUtility.SYMBOL, 0, 0);

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(quote);

        aaplService.calculateHighestChangeInMarketCapitalization();

        double expected = 0.0;

        assertThat(aaplService.getLastMarketCapitalizationRetrievedWithinInterval(), equalTo(quote.getMarketCapitalization()));

        assertThat(aaplService.retrieveChangeInMarketCapitalization(), equalTo(expected));

        Quote secondRandomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(secondRandomQuote);

        aaplService.calculateHighestChangeInMarketCapitalization();

        assertThat(aaplService.retrieveChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalizationRetrievedWithinInterval(), equalTo(secondRandomQuote.getMarketCapitalization()));
    }

    @Test
    public void testCalculateChangeInMarketCapitalizationWithInitialNullProceedingRunRandomQuote() {

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(null);

        aaplService.calculateHighestChangeInMarketCapitalization();

        double expected = 0.0;

        assertThat(aaplService.getLastMarketCapitalizationRetrievedWithinInterval(), equalTo(expected));

        assertThat(aaplService.retrieveChangeInMarketCapitalization(), equalTo(expected));

        Quote secondRandomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(secondRandomQuote);

        aaplService.calculateHighestChangeInMarketCapitalization();

        assertThat(aaplService.retrieveChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalizationRetrievedWithinInterval(), equalTo(secondRandomQuote.getMarketCapitalization()));
    }

    @Test
    public void testCalculateChangeInMarketCapitalizationWithInitialZeroQuoteProceedingRunRandomQuote() {

        Quote quote = new Quote(TestUtility.SYMBOL, 0, 0);

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(quote);

        aaplService.calculateHighestChangeInMarketCapitalization();

        double expected = 0.0;

        assertThat(aaplService.getLastMarketCapitalizationRetrievedWithinInterval(), equalTo(expected));

        assertThat(aaplService.retrieveChangeInMarketCapitalization(), equalTo(expected));

        Quote secondRandomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(secondRandomQuote);

        aaplService.calculateHighestChangeInMarketCapitalization();

        assertThat(aaplService.retrieveChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalizationRetrievedWithinInterval(), equalTo(secondRandomQuote.getMarketCapitalization()));
    }

    @Test
    public void testCalculateHighestChangeInMarketCapitalizationWithInitialRunRandomQuote() {
        Quote randomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(randomQuote);

        double expected = 0.0;

        aaplService.calculateHighestChangeInMarketCapitalization();

        assertThat(aaplService.getAggregateForHighestChangeInMarketCapitalization().getHighestChangeInMarketCapitalization(), equalTo(expected));
    }

    @Test
    public void testCalculateHighestChangeInMarketCapitalizationWithInitialRunRandomQuoteProceedingRunRandomQuote() {
        Quote randomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(randomQuote);

        double expected = 0.0;

        aaplService.calculateHighestChangeInMarketCapitalization();

        assertThat(aaplService.getAggregateForHighestChangeInMarketCapitalization().getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getMostRecentMarketCapitalization(), equalTo(randomQuote.getMarketCapitalization()));

        Quote secondRandomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(secondRandomQuote);

        aaplService.calculateHighestChangeInMarketCapitalization();

        expected = QuoteUtility.getCalculationOfChangeInMarketCapitalization(secondRandomQuote.getMarketCapitalization(), randomQuote.getMarketCapitalization());

        assertThat(aaplService.getAggregateForHighestChangeInMarketCapitalization().getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getMostRecentMarketCapitalization(), equalTo(secondRandomQuote.getMarketCapitalization()));
    }

    @Test
    public void testCalculateHighestChangeInMarketCapitalizationWithInitialRunNullProceedingRunRandomQuote() {

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(null);

        aaplService.calculateHighestChangeInMarketCapitalization();

        double expected = 0.0;

        assertThat(aaplService.getAggregateForHighestChangeInMarketCapitalization().getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getMostRecentMarketCapitalization(), equalTo(expected));

        Quote secondRandomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(secondRandomQuote);

        aaplService.calculateHighestChangeInMarketCapitalization();

        assertThat(aaplService.getAggregateForHighestChangeInMarketCapitalization().getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getMostRecentMarketCapitalization(), equalTo(secondRandomQuote.getMarketCapitalization()));
    }

    @Test
    public void testCalculateHighestChangeInMarketCapitalizationWithInitialRunZeroQuoteProceedingRunRandomQuote() {

        Quote quote = new Quote(TestUtility.SYMBOL, 0, 0);

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(quote);

        aaplService.calculateHighestChangeInMarketCapitalization();

        double expected = 0.0;

        assertThat(aaplService.getAggregateForHighestChangeInMarketCapitalization().getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getMostRecentMarketCapitalization(), equalTo(expected));

        Quote secondRandomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(secondRandomQuote);

        aaplService.calculateHighestChangeInMarketCapitalization();

        assertThat(aaplService.getAggregateForHighestChangeInMarketCapitalization().getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getMostRecentMarketCapitalization(), equalTo(secondRandomQuote.getMarketCapitalization()));
    }

    @Test
    public void testCalculateHighestChangeInMarketCapitalizationWithInitialRunRandomQuoteProceedingRunNull() {
        Quote randomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(randomQuote);

        aaplService.calculateHighestChangeInMarketCapitalization();

        double expected = 0.0;

        assertThat(aaplService.getAggregateForHighestChangeInMarketCapitalization().getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getMostRecentMarketCapitalization(), equalTo(randomQuote.getMarketCapitalization()));

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(null);

        aaplService.calculateHighestChangeInMarketCapitalization();

        assertThat(aaplService.getAggregateForHighestChangeInMarketCapitalization().getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getMostRecentMarketCapitalization(), equalTo(randomQuote.getMarketCapitalization()));
    }

    @Test
    public void testCalculateHighestChangeInMarketCapitalizationWithInitialRunRandomQuoteProceedingRunZeroQuote() {
        Quote randomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(randomQuote);

        aaplService.calculateHighestChangeInMarketCapitalization();

        double expected = 0.0;

        assertThat(aaplService.getAggregateForHighestChangeInMarketCapitalization().getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getMostRecentMarketCapitalization(), equalTo(randomQuote.getMarketCapitalization()));

        Quote secondQuote = new Quote(TestUtility.SYMBOL, 0, 0);

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(secondQuote);

        aaplService.calculateHighestChangeInMarketCapitalization();

        assertThat(aaplService.getAggregateForHighestChangeInMarketCapitalization().getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getMostRecentMarketCapitalization(), equalTo(randomQuote.getMarketCapitalization()));
    }

    @Test
    public void testCalculateHighestChangeInMarketCapitalizationWithRestClientException() {

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenThrow(new RestClientException(EXCEPTION));

        aaplService.calculateHighestChangeInMarketCapitalization();

        double expected = 0.0;

        assertThat(aaplService.getAggregateForHighestChangeInMarketCapitalization().getHighestChangeInMarketCapitalization(), equalTo(expected));
    }
}