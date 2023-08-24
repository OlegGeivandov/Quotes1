package com.example.quotes1.model;

import java.util.Date;

public class Candle {
    private String ticker;
    private String timing;
    private Double open;
    private Double close;
    private Double high;
    private Double low;
    private Double value;
    private Double volume;
    private Date begin;
    private Date end;

    public Candle(String ticker, String timing, Double open, Double close, Double high, Double low, Double value, Double volume, Date begin, Date end) {
        this.ticker = ticker;
        this.timing = timing;
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
        this.value = value;
        this.volume = volume;
        this.begin = begin;
        this.end = end;
    }
}
