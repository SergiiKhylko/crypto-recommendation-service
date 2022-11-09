package com.epam.skhylko.test.cryptorecommendationservice.services.impl;

import com.epam.skhylko.test.cryptorecommendationservice.entities.CryptoPrice;
import com.epam.skhylko.test.cryptorecommendationservice.entities.NormalizedRange;
import com.epam.skhylko.test.cryptorecommendationservice.mapper.CryptoPriceMapper;
import com.epam.skhylko.test.cryptorecommendationservice.mapper.impl.CryptoPriceMapperImpl;
import com.epam.skhylko.test.cryptorecommendationservice.model.CryptoPriceDto;
import com.epam.skhylko.test.cryptorecommendationservice.model.CryptoPricesDto;
import com.epam.skhylko.test.cryptorecommendationservice.model.NormalizedRangeDto;
import com.epam.skhylko.test.cryptorecommendationservice.model.NormalizedRangesDto;
import com.epam.skhylko.test.cryptorecommendationservice.repositories.CryptoPriceRepository;
import com.epam.skhylko.test.cryptorecommendationservice.utils.DateTimeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CryptoServiceImplTest {

    CryptoServiceImpl cryptoServiceImpl;

    @Mock
    CryptoPriceRepository repository;

    CryptoPriceMapper mapper;

    DateTimeUtil dateTimeUtil;

    CryptoPrice cryptoPrice;
    CryptoPrice cryptoPrice2;
    NormalizedRange normalizedRange;
    NormalizedRange normalizedRange2;

    @BeforeEach
    void setUp() {
        dateTimeUtil = new DateTimeUtil();
        mapper = new CryptoPriceMapperImpl(dateTimeUtil);
        cryptoServiceImpl = new CryptoServiceImpl(repository, mapper, dateTimeUtil);


        cryptoPrice = new CryptoPrice();
        cryptoPrice.setId(1L);
        cryptoPrice.setSymbol("BTC");
        cryptoPrice.setPrice(new BigDecimal("40000.00"));
        cryptoPrice.setTimestamp(dateTimeUtil.getCurrentTimestamp(ZoneOffset.UTC));

        cryptoPrice2 = new CryptoPrice();
        cryptoPrice2.setId(2L);
        cryptoPrice2.setSymbol("BTC");
        cryptoPrice2.setPrice(new BigDecimal("40001.00"));
        cryptoPrice2.setTimestamp(dateTimeUtil.getCurrentTimestamp(ZoneOffset.UTC));

        normalizedRange = new NormalizedRange();
        normalizedRange.setNormalizedPrice(new BigDecimal("123"));
        normalizedRange.setSymbol("BTC");

        normalizedRange2 = new NormalizedRange();
        normalizedRange2.setNormalizedPrice(new BigDecimal("234"));
        normalizedRange2.setSymbol("BTC");
    }

    @Test
    void getAll() {

        when(repository.findAll()).thenReturn(List.of(cryptoPrice, cryptoPrice2));
        CryptoPricesDto cryptoPricesDto = cryptoServiceImpl.getAll();

        assertEquals(2, cryptoPricesDto.getCryptoValues().size());
    }

    @Test
    void getByCrypto() {
        when(repository.findBySymbol("BTC")).thenReturn(List.of(cryptoPrice, cryptoPrice2, cryptoPrice));
        CryptoPricesDto cryptoPricesDto = cryptoServiceImpl.getByCrypto("BTC");
        assertEquals(3, cryptoPricesDto.getCryptoValues().size());
    }

    @Test
    void getMaxByCrypto() {
        when(repository.findTopBySymbolAndTimestampBetweenOrderByPriceDesc(
                anyString(), anyLong(), anyLong())).thenReturn(cryptoPrice);

        CryptoPriceDto cryptoPriceDto =
                cryptoServiceImpl.getMaxByCrypto("BTC", LocalDate.now(), LocalDate.now());

        assertEquals(cryptoPrice.getId(), cryptoPriceDto.getId());
        assertEquals(cryptoPrice.getSymbol(), cryptoPriceDto.getSymbol());
        assertEquals(cryptoPrice.getTimestamp(), cryptoPriceDto.getDateTime().toInstant(ZoneOffset.UTC).toEpochMilli());
        assertEquals(cryptoPrice.getPrice(), cryptoPriceDto.getPrice());
    }

    @Test
    void getMinByCrypto() {
        when(repository.findTopBySymbolAndTimestampBetweenOrderByPriceAsc(
                anyString(), anyLong(), anyLong())).thenReturn(cryptoPrice);

        CryptoPriceDto cryptoPriceDto =
                cryptoServiceImpl.getMinByCrypto("BTC", LocalDate.now(), LocalDate.now());

        assertEquals(cryptoPrice.getId(), cryptoPriceDto.getId());
        assertEquals(cryptoPrice.getSymbol(), cryptoPriceDto.getSymbol());
        assertEquals(cryptoPrice.getTimestamp(), cryptoPriceDto.getDateTime().toInstant(ZoneOffset.UTC).toEpochMilli());
        assertEquals(cryptoPrice.getPrice(), cryptoPriceDto.getPrice());
    }

    @Test
    void getOldestByCrypto() {
        when(repository.findTopBySymbolAndTimestampBetweenOrderByTimestampAsc(
                anyString(), anyLong(), anyLong())).thenReturn(cryptoPrice);

        CryptoPriceDto cryptoPriceDto =
                cryptoServiceImpl.getOldestByCrypto("BTC", LocalDate.now(), LocalDate.now());

        assertEquals(cryptoPrice.getId(), cryptoPriceDto.getId());
        assertEquals(cryptoPrice.getSymbol(), cryptoPriceDto.getSymbol());
        assertEquals(cryptoPrice.getTimestamp(), cryptoPriceDto.getDateTime().toInstant(ZoneOffset.UTC).toEpochMilli());
        assertEquals(cryptoPrice.getPrice(), cryptoPriceDto.getPrice());
    }

    @Test
    void getNewestByCrypto() {
        when(repository.findTopBySymbolAndTimestampBetweenOrderByTimestampDesc(
                anyString(), anyLong(), anyLong())).thenReturn(cryptoPrice);

        CryptoPriceDto cryptoPriceDto =
                cryptoServiceImpl.getNewestByCrypto("BTC", LocalDate.now(), LocalDate.now());

        assertEquals(cryptoPrice.getId(), cryptoPriceDto.getId());
        assertEquals(cryptoPrice.getSymbol(), cryptoPriceDto.getSymbol());
        assertEquals(cryptoPrice.getTimestamp(), cryptoPriceDto.getDateTime().toInstant(ZoneOffset.UTC).toEpochMilli());
        assertEquals(cryptoPrice.getPrice(), cryptoPriceDto.getPrice());
    }

    @Test
    void getNormalizedRanges() {
        when(repository.countNormalizeRanges()).thenReturn(List.of(normalizedRange,normalizedRange2, normalizedRange, normalizedRange));

        NormalizedRangesDto normalizedRangesDto = cryptoServiceImpl.getNormalizedRanges();
        assertEquals(4,normalizedRangesDto.getNormalizedRangesDto().size());
    }

    @Test
    void getMaxNormalizedRangeForDay() {
        when(repository.getMaxNormalizedRange(anyLong(), anyLong())).thenReturn(List.of(normalizedRange,normalizedRange2, normalizedRange, normalizedRange));
        NormalizedRangeDto normalizedRangesDto = cryptoServiceImpl.getMaxNormalizedRangeForDay(LocalDate.now());
        assertEquals(normalizedRangesDto.getNormalizedPrice(),normalizedRange.getNormalizedPrice());
        assertEquals(normalizedRangesDto.getSymbol(),normalizedRange.getSymbol());
    }
}