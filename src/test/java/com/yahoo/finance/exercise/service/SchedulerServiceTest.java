package com.yahoo.finance.exercise.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

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
        when(aaplService.calculateChangeInMarketCapitalization()).thenReturn(DUMMY_RESULT);

        schedulerService.printChangeInMarketCapitalization();

        verify(aaplService, atLeast(NUMBER_OF_INVOCATIONS)).calculateChangeInMarketCapitalization();

    }

    @Test
    public void testPrintHighestChangeInMarketCapitalization() {

        when(aaplService.calculateHighestChangeInMarketCapitalization()).thenReturn(DUMMY_RESULT);

        schedulerService.printHighestChangeInMarketCapitalization();

        verify(aaplService, atLeast(NUMBER_OF_INVOCATIONS)).calculateHighestChangeInMarketCapitalization();
    }
}