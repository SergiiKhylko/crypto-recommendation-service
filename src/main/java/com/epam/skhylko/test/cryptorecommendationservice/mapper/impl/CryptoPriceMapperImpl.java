package com.epam.skhylko.test.cryptorecommendationservice.mapper.impl;

import com.epam.skhylko.test.cryptorecommendationservice.entities.CryptoPrice;
import com.epam.skhylko.test.cryptorecommendationservice.entities.NormalizedRange;
import com.epam.skhylko.test.cryptorecommendationservice.mapper.CryptoPriceMapper;
import com.epam.skhylko.test.cryptorecommendationservice.model.CryptoPriceDto;
import com.epam.skhylko.test.cryptorecommendationservice.model.CryptoPricesDto;
import com.epam.skhylko.test.cryptorecommendationservice.model.NormalizedRangeDto;
import com.epam.skhylko.test.cryptorecommendationservice.model.NormalizedRangesDto;
import com.epam.skhylko.test.cryptorecommendationservice.utils.DateTimeUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Crypto Price Mapper implementation
 */
@Component
public class CryptoPriceMapperImpl implements CryptoPriceMapper {

    private final DateTimeUtil dateTimeUtil;

    public CryptoPriceMapperImpl(DateTimeUtil dateTimeUtil) {
        this.dateTimeUtil = dateTimeUtil;
    }

    /**
     * Mapping cryptoPrice entity to CryptoPriceDto
     * @param cryptoPrice
     * @return cryptoPriceDto
     */
    @Override
    public CryptoPriceDto cryptoPriceToCryptoPriceDto(CryptoPrice cryptoPrice) {
        if (cryptoPrice == null) {
            return null;
        }

        CryptoPriceDto cryptoPriceDto = new CryptoPriceDto();
        cryptoPriceDto.setId(cryptoPrice.getId());

        Long timestamp = cryptoPrice.getTimestamp();
        LocalDateTime dateTime = dateTimeUtil.timestampToLocalDateTime(timestamp, ZoneOffset.UTC);
        cryptoPriceDto.setDateTime(dateTime);
        cryptoPriceDto.setSymbol(cryptoPrice.getSymbol());
        cryptoPriceDto.setPrice(cryptoPrice.getPrice());
        return  cryptoPriceDto;
    }

    /**
     * Mapping cryptoPrices collection entity to CryptoPricesDto
     * @param cryptoPrices
     * @return cryptoPriceDto
     */
    @Override
    public CryptoPricesDto cryptoPricesToCryptoPricesDto(Collection<CryptoPrice> cryptoPrices) {
        if (cryptoPrices == null) {
            return null;
        }

        CryptoPricesDto cryptoPricesDto = new CryptoPricesDto();
        List<CryptoPriceDto> cryptoPriceDtoList = cryptoPrices.stream()
                .map(this::cryptoPriceToCryptoPriceDto).collect(Collectors.toList());
        cryptoPricesDto.setCryptoValues(cryptoPriceDtoList);

        return cryptoPricesDto;
    }

    /**
     * Mapping NormalizedRange entity to NormalizedRangeDto
     * @param range
     * @return rangeDto
     */
    @Override
    public NormalizedRangeDto normalizedRageToNormalizedRageDto(NormalizedRange range) {
        if (range == null) {
            return null;
        }

        NormalizedRangeDto rangeDto = new NormalizedRangeDto();
        rangeDto.setNormalizedPrice(range.getNormalizedPrice());
        rangeDto.setSymbol(range.getSymbol());
        return rangeDto;
    }

    /**
     * Mapping NormalizedRange collection to NormalizedRangesDto
     * @param normalizedRages
     * @return rangesDto
     */
    @Override
    public NormalizedRangesDto normalizedRagesToNormalizedRagesDto(Collection<NormalizedRange> normalizedRages) {
        if (normalizedRages == null) {
            return null;
        }

        NormalizedRangesDto rangesDto = new NormalizedRangesDto();
        List<NormalizedRangeDto> normalizedRangeDtoList =
                normalizedRages.stream()
                        .map(this::normalizedRageToNormalizedRageDto)
                        .collect(Collectors.toList());
        rangesDto.setNormalizedRangesDto(normalizedRangeDtoList);

        return rangesDto;
    }
}
