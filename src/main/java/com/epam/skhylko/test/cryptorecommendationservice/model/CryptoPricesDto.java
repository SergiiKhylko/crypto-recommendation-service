package com.epam.skhylko.test.cryptorecommendationservice.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

public class CryptoPricesDto {

    @ApiModelProperty(value = "List of CryptoPrices")
    private List<CryptoPriceDto> cryptoValues = new ArrayList<>();

    public CryptoPricesDto() {
    }

    public CryptoPricesDto(List<CryptoPriceDto> cryptoValues) {
        this.cryptoValues = cryptoValues;
    }

    public List<CryptoPriceDto> getCryptoValues() {
        return cryptoValues;
    }

    public void setCryptoValues(List<CryptoPriceDto> cryptoValues) {
        this.cryptoValues = cryptoValues;
    }
}
