package com.epam.skhylko.test.cryptorecommendationservice.repositories;

import com.epam.skhylko.test.cryptorecommendationservice.entities.CryptoPrice;
import com.epam.skhylko.test.cryptorecommendationservice.entities.NormalizedRange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface CryptoPriceRepository extends JpaRepository<CryptoPrice, Long> {

    /**
     * returns all cryptos by symbol
     * @param symbol
     */
    List<CryptoPrice> findBySymbol(String symbol);

    /**
     * returns the crypto with the highest price between timestamps
     * @param symbol
     * @param timestampStart
     * @param timestampEnd
     */
    CryptoPrice findTopBySymbolAndTimestampBetweenOrderByPriceDesc(String symbol, Long timestampStart, Long timestampEnd);

    /**
     * returns the crypto with the lowest price between timestamps
     * @param symbol
     * @param timestampStart
     * @param timestampEnd
     */
    CryptoPrice findTopBySymbolAndTimestampBetweenOrderByPriceAsc(String symbol, Long timestampStart, Long timestampEnd);

    /**
     * returns the crypto with the lowest timestamp between timestamps params
     * @param symbol
     * @param timestampStart
     * @param timestampEnd
     */
    CryptoPrice findTopBySymbolAndTimestampBetweenOrderByTimestampAsc(String symbol, Long timestampStart, Long timestampEnd);

    /**
     * returns the crypto with the highest timestamp between timestamps params
     * @param symbol
     * @param timestampStart
     * @param timestampEnd
     */
    CryptoPrice findTopBySymbolAndTimestampBetweenOrderByTimestampDesc(String symbol, Long timestampStart, Long timestampEnd);


    /**
     * returns the sorting normalize range of all cryptos
     */
    @Query("select " +
            "new com.epam.skhylko.test.cryptorecommendationservice.entities.NormalizedRange(" +
            "(max(crypto.price) + min(crypto.price))/min(crypto.price) as normalizedPrice," +
            "crypto.symbol)" +
            " from CryptoPrice as crypto group by crypto.symbol " +
            "order by normalizedPrice")
    Collection<NormalizedRange> countNormalizeRanges();

    /**
     * returns the highest normalize range between timestamps params
     */
    //TODO redo to return only one value
    @Query("select " +
            "new com.epam.skhylko.test.cryptorecommendationservice.entities.NormalizedRange(" +
            "(max(crypto.price) - min(crypto.price))/min(crypto.price) as normalizedPrice," +
            " crypto.symbol) " +
            "from CryptoPrice as crypto " +
            "where timestamp > :startTimestamp and timestamp < :endTimestamp group by crypto.symbol " +
            "  order by normalizedPrice desc")
    List<NormalizedRange> getMaxNormalizedRange(@Param(value = "startTimestamp") Long startTimestamp,
                                                @Param(value = "endTimestamp") Long endTimestamp);
}
