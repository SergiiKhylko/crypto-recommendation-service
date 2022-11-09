package com.epam.skhylko.test.cryptorecommendationservice.model;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class NormalizedRangeDto {
    @ApiModelProperty(value = "Normalized Price of crypto ((max-min)/min)")
    private BigDecimal normalizedPrice;
    @ApiModelProperty(value = "Title of crypto")
    private String symbol;

    public NormalizedRangeDto(BigDecimal normalizedPrice, String symbol) {
        this.normalizedPrice = normalizedPrice;
        this.symbol = symbol;
    }

    public NormalizedRangeDto() {
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
