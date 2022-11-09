package com.epam.skhylko.test.cryptorecommendationservice.services.impl;

import com.epam.skhylko.test.cryptorecommendationservice.entities.NormalizedRange;
import com.epam.skhylko.test.cryptorecommendationservice.mapper.CryptoPriceMapper;
import com.epam.skhylko.test.cryptorecommendationservice.model.CryptoPriceDto;
import com.epam.skhylko.test.cryptorecommendationservice.model.CryptoPricesDto;
import com.epam.skhylko.test.cryptorecommendationservice.model.NormalizedRangeDto;
import com.epam.skhylko.test.cryptorecommendationservice.model.NormalizedRangesDto;
import com.epam.skhylko.test.cryptorecommendationservice.repositories.CryptoPriceRepository;
import com.epam.skhylko.test.cryptorecommendationservice.services.CryptoService;
import com.epam.skhylko.test.cryptorecommendationservice.utils.DateTimeUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.List;

@Service
public class CryptoServiceImpl implements CryptoService {

    private final static ZoneOffset ZONE_OFF_SET = ZoneOffset.UTC;

    private final CryptoPriceRepository repository;
    private final CryptoPriceMapper mapper;
    private final DateTimeUtil dateTimeUtil;

    public CryptoServiceImpl(CryptoPriceRepository repository, CryptoPriceMapper mapper, DateTimeUtil dateTimeUtil) {
        this.repository = repository;
        this.mapper = mapper;
        this.dateTimeUtil = dateTimeUtil;
    }

    /**
     * returns all crypto prices
     */
    @Override
    public CryptoPricesDto getAll() {
        return mapper.cryptoPricesToCryptoPricesDto(repository.findAll());
    }

    /**
     * returns prices by Crypto
     * @param crypto
     * @return cryptoPricesDto
     */
    @Override
    public CryptoPricesDto getByCrypto(String crypto) {
        return mapper.cryptoPricesToCryptoPricesDto(repository.findBySymbol(crypto));
    }

    /**
     * returns the highest prices by Crypto between dates
     * @param crypto
     * @param from if null, then current month start is set
     * @param to if null, then current day is set
     * @return cryptoPriceDto
     */
    @Override
    public CryptoPriceDto getMaxByCrypto(String crypto, LocalDate from, LocalDate to) {
        Long startTimestamp = getMonthStartTimestamp(from, ZONE_OFF_SET);
        Long endTimestamp = getEndTimestamp(to, ZONE_OFF_SET);

        return mapper.cryptoPriceToCryptoPriceDto(
                repository.findTopBySymbolAndTimestampBetweenOrderByPriceDesc(crypto, startTimestamp, endTimestamp));
    }

    /**
     * returns the lowest prices by Crypto between dates
     * @param crypto
     * @param from if null, then current month start is set
     * @param to if null, then current day is set
     * @return cryptoPriceDto
     */
    @Override
    public CryptoPriceDto getMinByCrypto(String crypto, LocalDate from, LocalDate to) {
        Long startTimestamp = getMonthStartTimestamp(from, ZONE_OFF_SET);
        Long endTimestamp = getEndTimestamp(to, ZONE_OFF_SET);

        return mapper.cryptoPriceToCryptoPriceDto(
                repository.findTopBySymbolAndTimestampBetweenOrderByPriceAsc(crypto, startTimestamp, endTimestamp));
    }

    /**
     * returns the oldest prices by Crypto between dates
     * @param crypto
     * @param from if null, then current month start is set
     * @param to if null, then current day is set
     * @return cryptoPriceDto
     */
    @Override
    public CryptoPriceDto getOldestByCrypto(String crypto, LocalDate from, LocalDate to) {
        Long startTimestamp = getMonthStartTimestamp(from, ZONE_OFF_SET);
        Long endTimestamp = getEndTimestamp(to, ZONE_OFF_SET);

        return mapper.cryptoPriceToCryptoPriceDto(
                repository.findTopBySymbolAndTimestampBetweenOrderByTimestampAsc(crypto, startTimestamp, endTimestamp));
    }

    /**
     * returns the newest prices by Crypto between dates
     * @param crypto
     * @param from if null, then current month start is set
     * @param to if null, then current day is set
     */
    @Override
    public CryptoPriceDto getNewestByCrypto(String crypto, LocalDate from, LocalDate to) {
        Long startTimestamp = getMonthStartTimestamp(from, ZONE_OFF_SET);
        Long endTimestamp = getEndTimestamp(to, ZONE_OFF_SET);

        return mapper.cryptoPriceToCryptoPriceDto(
                repository.findTopBySymbolAndTimestampBetweenOrderByTimestampDesc(crypto, startTimestamp, endTimestamp));
    }


    /**
     * returns the sorting normalize range of all cryptos
     */
    @Override
    public NormalizedRangesDto getNormalizedRanges() {
        Collection<NormalizedRange> normalizedRages = repository.countNormalizeRanges();
        return mapper.normalizedRagesToNormalizedRagesDto(normalizedRages);
    }

    /**
     * returns the highest normalize range at the specific date
     * @param date if null, then current day is set
     */
    @Override
    public NormalizedRangeDto getMaxNormalizedRangeForDay(LocalDate date) {
        Long startTimestamp = getStartOfTheDayTimestamp(date, ZONE_OFF_SET);
        Long endTimestamp = getEndOfTheDayTimestamp(date, ZONE_OFF_SET);
        List<NormalizedRange> normalizedRanges = repository.getMaxNormalizedRange(startTimestamp, endTimestamp);
        NormalizedRange normalizedRange = normalizedRanges.stream().findFirst().orElse(null);
        return mapper.normalizedRageToNormalizedRageDto(normalizedRange);
    }

    private Long getMonthStartTimestamp(LocalDate from, ZoneOffset offset) {
        return from == null
                ? dateTimeUtil.getStartOfCurrentMonthTimestamp(offset)
                : dateTimeUtil.localDateToTimestamp(from, offset);
    }

    private Long getEndTimestamp(LocalDate to, ZoneOffset offset) {
        return to == null
                ? dateTimeUtil.getCurrentTimestamp(offset)
                : dateTimeUtil.localDateToTimestamp(to, offset);
    }

    private Long getStartOfTheDayTimestamp(LocalDate date, ZoneOffset offset) {
        return date == null
                ? dateTimeUtil.getTodayTimestamp(offset)
                : dateTimeUtil.localDateToTimestamp(date, offset);
    }

    private Long getEndOfTheDayTimestamp(LocalDate date, ZoneOffset offset) {
        return date == null
                ? dateTimeUtil.getCurrentTimestamp(offset)
                : dateTimeUtil.getEndDayTimestamp(date, offset);
    }
}
