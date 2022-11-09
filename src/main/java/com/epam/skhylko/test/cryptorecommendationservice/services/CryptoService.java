package com.epam.skhylko.test.cryptorecommendationservice.services;

import com.epam.skhylko.test.cryptorecommendationservice.model.CryptoPriceDto;
import com.epam.skhylko.test.cryptorecommendationservice.model.CryptoPricesDto;
import com.epam.skhylko.test.cryptorecommendationservice.model.NormalizedRangeDto;
import com.epam.skhylko.test.cryptorecommendationservice.model.NormalizedRangesDto;

import java.time.LocalDate;

public interface CryptoService {
    CryptoPricesDto getAll();

    CryptoPricesDto getByCrypto(String crypto);

    CryptoPriceDto getMaxByCrypto(String crypto, LocalDate from, LocalDate to);

    CryptoPriceDto getMinByCrypto(String crypto, LocalDate from, LocalDate to);

    CryptoPriceDto getOldestByCrypto(String crypto, LocalDate from, LocalDate to);

    CryptoPriceDto getNewestByCrypto(String crypto, LocalDate from, LocalDate to);

    NormalizedRangesDto getNormalizedRanges();

    NormalizedRangeDto getMaxNormalizedRangeForDay(LocalDate date);
}
