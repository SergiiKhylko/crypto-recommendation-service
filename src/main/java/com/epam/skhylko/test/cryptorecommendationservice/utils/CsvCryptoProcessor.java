package com.epam.skhylko.test.cryptorecommendationservice.utils;

import com.epam.skhylko.test.cryptorecommendationservice.entities.CryptoPrice;

/**
 * CsvCryptoProcessor functional interface to sent
 * callback to process saving records from csv to db
 */
@FunctionalInterface
public interface CsvCryptoProcessor {
    void process(CryptoPrice cryptoPrice);
}
