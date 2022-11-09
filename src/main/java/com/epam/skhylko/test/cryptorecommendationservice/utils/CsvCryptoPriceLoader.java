package com.epam.skhylko.test.cryptorecommendationservice.utils;

import com.epam.skhylko.test.cryptorecommendationservice.repositories.CryptoPriceRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Csv CryptoPrice Loader, loads data from csv files to database
 */
@Component
public class CsvCryptoPriceLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final CryptoPriceRepository repository;
    private final CsvCryptoPriceUtil util;

    public CsvCryptoPriceLoader(CryptoPriceRepository repository, CsvCryptoPriceUtil util) {
        this.repository = repository;
        this.util = util;
    }

    /**
     * sent callback function to csv processor
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        util.processCsvFiles(repository::save);
    }
}
