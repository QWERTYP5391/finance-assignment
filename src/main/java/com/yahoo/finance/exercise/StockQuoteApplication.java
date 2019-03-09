package com.yahoo.finance.exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StockQuoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockQuoteApplication.class, args);
    }

}
