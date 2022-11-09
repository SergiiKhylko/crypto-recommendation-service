package com.epam.skhylko.test.cryptorecommendationservice.controllers;

import com.epam.skhylko.test.cryptorecommendationservice.model.CryptoPriceDto;
import com.epam.skhylko.test.cryptorecommendationservice.model.CryptoPricesDto;
import com.epam.skhylko.test.cryptorecommendationservice.model.NormalizedRangeDto;
import com.epam.skhylko.test.cryptorecommendationservice.model.NormalizedRangesDto;
import com.epam.skhylko.test.cryptorecommendationservice.services.CryptoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * Crypto Price Controller
 */
@RestController
@RequestMapping(CryptoPriceController.BASE_URL)
public class CryptoPriceController {

    public static final String BASE_URL = "/api/cryptos";

    private final CryptoService cryptoService;

    public CryptoPriceController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @ApiOperation(value = "Returns all saved cryptos from database")
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public CryptoPricesDto getAllCryptos(){
        return cryptoService.getAll();
    }

    @ApiOperation(value = "Returns all saved cryptos from database by crypto's name")
    @GetMapping("/{crypto}")
    @ResponseStatus(HttpStatus.OK)
    public CryptoPricesDto getCryptosByName(@PathVariable String crypto){
        return cryptoService.getByCrypto(crypto);
    }

    @ApiOperation(value = "Returns a descending sorted list of all the cryptos,\n" +
            "comparing the normalized range (max-min)/min")
    @GetMapping("/normalized")
    @ResponseStatus(HttpStatus.OK)
    public NormalizedRangesDto getNormalizedRanges(){
        return cryptoService.getNormalizedRanges();
    }

    @ApiOperation(value = "Returns the crypto with the highest normalized range for a\n" +
            "specific day (current day by default). Date format by default 'YYYY-MM-dd'")
    @GetMapping("/normalized/max")
    @ResponseStatus(HttpStatus.OK)
    public NormalizedRangeDto getMaxNormalizedRangeForDay(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date){
        return cryptoService.getMaxNormalizedRangeForDay(date);
    }

    @ApiOperation(value = "Returns the crypto with the highest price during date range (current month by default). Date format by default 'YYYY-MM-dd'")
    @GetMapping("/{crypto}/max")
    @ResponseStatus(HttpStatus.OK)
    public CryptoPriceDto getCryptosByNameMax(@PathVariable String crypto,
                                                  @RequestParam(required = false)
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                  LocalDate from,
                                                  @RequestParam(required = false)
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                  LocalDate to){
        return cryptoService.getMaxByCrypto(crypto, from, to);
    }

    @ApiOperation(value = "Returns the crypto with the lowest price during date range (current month by default). Date format by default 'YYYY-MM-dd'")
    @GetMapping("/{crypto}/min")
    @ResponseStatus(HttpStatus.OK)
    public CryptoPriceDto getCryptosByNameMin(@PathVariable String crypto,
                                                  @RequestParam(required = false)
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                  LocalDate from,
                                                  @RequestParam(required = false)
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                  LocalDate to){
        return cryptoService.getMinByCrypto(crypto, from, to);
    }

    @ApiOperation(value = "Returns the oldest saved crypto during date range (current month by default). Date format by default 'YYYY-MM-dd'")
    @GetMapping("/{crypto}/oldest")
    @ResponseStatus(HttpStatus.OK)
    public CryptoPriceDto getCryptosByNameOldest(@PathVariable String crypto,
                                                     @RequestParam(required = false)
                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                     LocalDate from,
                                                     @RequestParam(required = false)
                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                     LocalDate to){
        return cryptoService.getOldestByCrypto(crypto, from, to);
    }

    @ApiOperation(value = "Returns the newest saved crypto during date range (current month by default). Date format by default 'YYYY-MM-dd'")
    @GetMapping("/{crypto}/newest")
    @ResponseStatus(HttpStatus.OK)
    public CryptoPriceDto getCryptosByNameNewest(@PathVariable String crypto,
                                                    @RequestParam(required = false)
                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                    LocalDate from,
                                                    @RequestParam(required = false)
                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                    LocalDate to){
        return cryptoService.getNewestByCrypto(crypto, from, to);
    }
}
