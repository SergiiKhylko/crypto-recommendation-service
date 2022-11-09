package com.epam.skhylko.test.cryptorecommendationservice.entities;

import java.math.BigDecimal;

/**
 * Normalized Range
 * POJO for returning grouped structure by crypto  with normalized price
 */
public class NormalizedRange {
    private BigDecimal normalizedPrice;
    private String symbol;

    public NormalizedRange(BigDecimal normalizedPrice, String symbol) {
        this.normalizedPrice = normalizedPrice;
        this.symbol = symbol;
    }

    public NormalizedRange() {
    }

    public BigDecimal getNormalizedPrice() {
        return normalizedPrice;
    }

    public void setNormalizedPrice(BigDecimal normalizedPrice) {
        this.normalizedPrice = normalizedPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
