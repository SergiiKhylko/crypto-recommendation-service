package com.epam.skhylko.test.cryptorecommendationservice.entities;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class CryptoPrice extends BaseEntity{

    @CsvBindByName
    private Long timestamp;

    @CsvBindByName
    private String symbol;

    @CsvBindByName
    private BigDecimal price;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timeStamp) {
        this.timestamp = timeStamp;
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
