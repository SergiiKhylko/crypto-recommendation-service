package com.epam.skhylko.test.cryptorecommendationservice.mapper.impl;

import com.epam.skhylko.test.cryptorecommendationservice.entities.CryptoPrice;
import com.epam.skhylko.test.cryptorecommendationservice.entities.NormalizedRange;
import com.epam.skhylko.test.cryptorecommendationservice.model.CryptoPriceDto;
import com.epam.skhylko.test.cryptorecommendationservice.model.CryptoPricesDto;
import com.epam.skhylko.test.cryptorecommendationservice.model.NormalizedRangeDto;
import com.epam.skhylko.test.cryptorecommendationservice.model.NormalizedRangesDto;
import com.epam.skhylko.test.cryptorecommendationservice.utils.DateTimeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CryptoPriceMapperImplTest {

    CryptoPriceMapperImpl cryptoPriceMapper;

    DateTimeUtil dateTimeUtil;

    @BeforeEach
    void setUp() {
        dateTimeUtil = new DateTimeUtil();
        cryptoPriceMapper = new CryptoPriceMapperImpl(dateTimeUtil);
    }

    @Test
    void cryptoPriceToCryptoPriceDto() {
        CryptoPrice cryptoPrice = new CryptoPrice();
        cryptoPrice.setId(1L);
        cryptoPrice.setSymbol("BTC");
        cryptoPrice.setPrice(new BigDecimal("40000.00"));
        cryptoPrice.setTimestamp(dateTimeUtil.getCurrentTimestamp(ZoneOffset.UTC));

        CryptoPriceDto cryptoPriceDto = cryptoPriceMapper.cryptoPriceToCryptoPriceDto(cryptoPrice);

        assertEquals(cryptoPrice.getId(), cryptoPriceDto.getId());
        assertEquals(cryptoPrice.getSymbol(), cryptoPriceDto.getSymbol());
        assertEquals(cryptoPrice.getTimestamp(), cryptoPriceDto.getDateTime().toInstant(ZoneOffset.UTC).toEpochMilli());
        assertEquals(cryptoPrice.getPrice(), cryptoPriceDto.getPrice());
    }

    @Test
    void cryptoPricesToCryptoPricesDto() {
        CryptoPrice cryptoPrice = new CryptoPrice();
        cryptoPrice.setId(1L);
        cryptoPrice.setSymbol("BTC");
        cryptoPrice.setPrice(new BigDecimal("40000.00"));
        cryptoPrice.setTimestamp(dateTimeUtil.getCurrentTimestamp(ZoneOffset.UTC));

        CryptoPrice cryptoPrice2 = new CryptoPrice();
        cryptoPrice2.setId(2L);
        cryptoPrice2.setSymbol("BTC");
        cryptoPrice2.setPrice(new BigDecimal("40001.00"));
        cryptoPrice2.setTimestamp(dateTimeUtil.getCurrentTimestamp(ZoneOffset.UTC));

        CryptoPricesDto cryptoPricesDto =
                cryptoPriceMapper.cryptoPricesToCryptoPricesDto(Arrays.asList(cryptoPrice, cryptoPrice2));

        assertEquals(2, cryptoPricesDto.getCryptoValues().size());
    }

    @Test
    void normalizedRageToNormalizedRageDto() {

        NormalizedRange normalizedRange = new NormalizedRange();
        normalizedRange.setNormalizedPrice(new BigDecimal("123"));
        normalizedRange.setSymbol("BTC");

        NormalizedRangeDto normalizedRangeDto = cryptoPriceMapper.normalizedRageToNormalizedRageDto(normalizedRange);

        assertEquals(normalizedRange.getNormalizedPrice(), normalizedRangeDto.getNormalizedPrice());
        assertEquals(normalizedRange.getSymbol(), normalizedRangeDto.getSymbol());

    }

    @Test
    void normalizedRagesToNormalizedRagesDto() {
        NormalizedRange normalizedRange = new NormalizedRange();
        normalizedRange.setNormalizedPrice(new BigDecimal("123"));
        normalizedRange.setSymbol("BTC");

        NormalizedRange normalizedRange2 = new NormalizedRange();
        normalizedRange2.setNormalizedPrice(new BigDecimal("234"));
        normalizedRange2.setSymbol("BTC");

        NormalizedRangesDto normalizedRangesDto = cryptoPriceMapper.normalizedRagesToNormalizedRagesDto(List.of(normalizedRange, normalizedRange2));

        assertEquals(2, normalizedRangesDto.getNormalizedRangesDto().size());
    }
}