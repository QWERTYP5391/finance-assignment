package com.yahoo.finance.exercise.model;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class QuoteAggregate {

    @NonNull
    private double highestChangeInMarketCapitalization;

    @NonNull
    private LocalDateTime localDateTime;
}
