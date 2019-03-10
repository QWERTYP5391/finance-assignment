package com.yahoo.finance.exercise.service;

import com.yahoo.finance.exercise.model.QuoteAggregate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SchedulerServiceTest {

    private static final double DUMMY_RESULT = 100.0;

    private static final int NUMBER_OF_INVOCATIONS = 1;

    @MockBean
    AAPLService aaplService;

    @Autowired
    SchedulerService schedulerService;

    @Test
    public void testPrintChangeInMarketCapitalization() {
        when(aaplService.retrieveChangeInMarketCapitalization()).thenReturn(DUMMY_RESULT);

        when(aaplService.getAggregateForHighestChangeInMarketCapitalization()).thenReturn(new QuoteAggregate(0, LocalDateTime.now()));

        schedulerService.printChangeInMarketCapitalization();

        verify(aaplService, atLeast(NUMBER_OF_INVOCATIONS)).retrieveChangeInMarketCapitalization();

        verify(aaplService, atLeast(NUMBER_OF_INVOCATIONS)).getAggregateForHighestChangeInMarketCapitalization();

    }

    @Test
    public void testPrintChangeInMarketCapitalizationWithQuoteAggregateNull() {
        when(aaplService.retrieveChangeInMarketCapitalization()).thenReturn(DUMMY_RESULT);

        when(aaplService.getAggregateForHighestChangeInMarketCapitalization()).thenReturn(null);

        schedulerService.printChangeInMarketCapitalization();

        verify(aaplService, atLeast(NUMBER_OF_INVOCATIONS)).retrieveChangeInMarketCapitalization();

       verify(aaplService, atLeast(NUMBER_OF_INVOCATIONS)).getAggregateForHighestChangeInMarketCapitalization();

    }

    @Test
    public void testPrintHighestChangeInMarketCapitalization() {

        schedulerService.pollApi();

        verify(aaplService, atLeast(NUMBER_OF_INVOCATIONS)).calculateHighestChangeInMarketCapitalization();
    }
}