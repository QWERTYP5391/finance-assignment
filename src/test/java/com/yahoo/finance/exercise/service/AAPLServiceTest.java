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
        aaplService.setLastMarketCapitalizationForHighestChange(0.0);
        aaplService.setLastChangeInMarketCapitalization(0.0);
        aaplService.setLastMarketCapitalization(0.0);
    }

    @Test
    public void testCalculateChangeInMarketCapitalizationWithInitialRunRandomQuote() {
        Quote randomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(randomQuote);

        double expected = 0.0;

        assertThat(aaplService.calculateChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalization(), equalTo(randomQuote.getMarketCapitalization()));
    }

    @Test
    public void testCalculateChangeInMarketCapitalizationWithInitialRunRandomQuoteProceedingRunRandomQuote() {
        Quote randomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(randomQuote);

        double expected = 0.0;

        assertThat(aaplService.calculateChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalization(), equalTo(randomQuote.getMarketCapitalization()));

        Quote secondRandomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(secondRandomQuote);

        expected = QuoteUtility.getCalculationOfChangeInMarketCapitalization(secondRandomQuote.getMarketCapitalization(), randomQuote.getMarketCapitalization());

        assertThat(aaplService.calculateChangeInMarketCapitalization(), equalTo(expected));
    }

    @Test
    public void testCalculateChangeInMarketCapitalizationWithInitialRunNullProceedingRunRandomQuote() {

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(null);

        double expected = 0.0;

        assertThat(aaplService.getLastMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.calculateChangeInMarketCapitalization(), equalTo(expected));

        Quote secondRandomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(secondRandomQuote);

        assertThat(aaplService.calculateChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalization(), equalTo(secondRandomQuote.getMarketCapitalization()));
    }

    @Test
    public void testCalculateChangeInMarketCapitalizationWithInitialRunZeroQuoteProceedingRunRandomQuote() {

        Quote randomQuote =  new Quote(TestUtility.SYMBOL, 0, 0);

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(randomQuote);

        double expected = 0.0;

        assertThat(aaplService.getLastMarketCapitalization(), equalTo(randomQuote.getMarketCapitalization()));

        assertThat(aaplService.calculateChangeInMarketCapitalization(), equalTo(expected));

        Quote secondRandomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(secondRandomQuote);

        assertThat(aaplService.calculateChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalization(), equalTo(secondRandomQuote.getMarketCapitalization()));
    }

    @Test
    public void testCalculateHighestChangeInMarketCapitalizationWithInitialRunRandomQuote() {
        Quote randomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(randomQuote);

        double expected = 0.0;

        assertThat(aaplService.calculateHighestChangeInMarketCapitalization().getHighestChangeInMarketCapitalization(), equalTo(expected));
    }

    @Test
    public void testCalculateHighestChangeInMarketCapitalizationWithInitialRunRandomQuoteProceedingRunRandomQuote() {
        Quote randomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(randomQuote);

        double expected = 0.0;

        assertThat(aaplService.calculateHighestChangeInMarketCapitalization().getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalizationForHighestChange(), equalTo(randomQuote.getMarketCapitalization()));

        Quote secondRandomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(secondRandomQuote);

        expected = QuoteUtility.getCalculationOfChangeInMarketCapitalization(secondRandomQuote.getMarketCapitalization(), randomQuote.getMarketCapitalization());

        assertThat(aaplService.calculateHighestChangeInMarketCapitalization().getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalizationForHighestChange(), equalTo(secondRandomQuote.getMarketCapitalization()));
    }

    @Test
    public void testCalculateHighestChangeInMarketCapitalizationWithInitialRunNullProceedingRunRandomQuote() {

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(null);

        double expected = 0.0;

        assertThat(aaplService.calculateHighestChangeInMarketCapitalization().getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalizationForHighestChange(), equalTo(expected));

        Quote secondRandomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(secondRandomQuote);

        assertThat(aaplService.calculateHighestChangeInMarketCapitalization().getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalizationForHighestChange(), equalTo(secondRandomQuote.getMarketCapitalization()));
    }

    @Test
    public void testCalculateHighestChangeInMarketCapitalizationWithInitialRunZeroQuoteProceedingRunRandomQuote() {

        Quote quote = new Quote(TestUtility.SYMBOL, 0, 0);

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(quote);

        double expected = 0.0;

        assertThat(aaplService.calculateHighestChangeInMarketCapitalization().getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalizationForHighestChange(), equalTo(expected));

        Quote secondRandomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(secondRandomQuote);

        assertThat(aaplService.calculateHighestChangeInMarketCapitalization().getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalizationForHighestChange(), equalTo(secondRandomQuote.getMarketCapitalization()));
    }

    @Test
    public void testCalculateHighestChangeInMarketCapitalizationWithInitialRunRandomQuoteProceedingRunNull() {
        Quote randomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(randomQuote);

        double expected = 0.0;

        assertThat(aaplService.calculateHighestChangeInMarketCapitalization().getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalizationForHighestChange(), equalTo(randomQuote.getMarketCapitalization()));

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(null);

        assertThat(aaplService.calculateHighestChangeInMarketCapitalization().getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalizationForHighestChange(), equalTo(randomQuote.getMarketCapitalization()));
    }

    @Test
    public void testCalculateHighestChangeInMarketCapitalizationWithInitialRunRandomQuoteProceedingRunZeroQuote() {
        Quote randomQuote = TestUtility.getRandomQuote();

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(randomQuote);

        double expected = 0.0;

        assertThat(aaplService.calculateHighestChangeInMarketCapitalization().getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalizationForHighestChange(), equalTo(randomQuote.getMarketCapitalization()));

        Quote secondQuote = new Quote(TestUtility.SYMBOL, 0, 0);

        when(mockRestTemplate.getForObject(aaplService.getApiEndpoint(), Quote.class)).thenReturn(secondQuote);

        assertThat(aaplService.calculateHighestChangeInMarketCapitalization().getHighestChangeInMarketCapitalization(), equalTo(expected));

        assertThat(aaplService.getLastMarketCapitalizationForHighestChange(), equalTo(randomQuote.getMarketCapitalization()));
    }
}