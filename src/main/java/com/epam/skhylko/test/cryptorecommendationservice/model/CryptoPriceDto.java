package com.epam.skhylko.test.cryptorecommendationservice.model;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CryptoPriceDto {

    @ApiModelProperty(value = "Crypto Price ID for database")
    private Long id;

    @ApiModelProperty(value = "dateTime converted from timestamp ")
    private LocalDateTime dateTime;

    @ApiModelProperty(value = "Symbol of Crypto")
    private String symbol;

    @ApiModelProperty(value = "Established crypto price at the dateTime")
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
