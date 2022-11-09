package com.epam.skhylko.test.cryptorecommendationservice.mapper;

import com.epam.skhylko.test.cryptorecommendationservice.entities.CryptoPrice;
import com.epam.skhylko.test.cryptorecommendationservice.entities.NormalizedRange;
import com.epam.skhylko.test.cryptorecommendationservice.model.CryptoPriceDto;
import com.epam.skhylko.test.cryptorecommendationservice.model.CryptoPricesDto;
import com.epam.skhylko.test.cryptorecommendationservice.model.NormalizedRangeDto;
import com.epam.skhylko.test.cryptorecommendationservice.model.NormalizedRangesDto;

import java.util.Collection;

/**
 * Crypto Price Mapper interface
 */
public interface CryptoPriceMapper {
     CryptoPriceDto cryptoPriceToCryptoPriceDto(CryptoPrice cryptoPrice);
     CryptoPricesDto cryptoPricesToCryptoPricesDto(Collection<CryptoPrice> cryptoPrices);

     NormalizedRangeDto normalizedRageToNormalizedRageDto(NormalizedRange normalizedRange);

     NormalizedRangesDto normalizedRagesToNormalizedRagesDto(Collection<NormalizedRange> normalizedRages);
}
