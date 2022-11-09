package com.epam.skhylko.test.cryptorecommendationservice.controllers;

import com.epam.skhylko.test.cryptorecommendationservice.model.CryptoPriceDto;
import com.epam.skhylko.test.cryptorecommendationservice.model.CryptoPricesDto;
import com.epam.skhylko.test.cryptorecommendationservice.model.NormalizedRangeDto;
import com.epam.skhylko.test.cryptorecommendationservice.model.NormalizedRangesDto;
import com.epam.skhylko.test.cryptorecommendationservice.services.CryptoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CryptoPriceController.class)
class CryptoPriceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CryptoService cryptoService;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {


    }

    @Test
    void getAllCryptos() throws Exception {
        given(cryptoService.getAll()).willReturn(new CryptoPricesDto());
        mockMvc.perform(get("/api/cryptos").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getCryptosByName() throws Exception {
        given(cryptoService.getByCrypto(any())).willReturn(new CryptoPricesDto());
        mockMvc.perform(get("/api/cryptos/BTC").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getNormalizedRanges() throws Exception {
        given(cryptoService.getNormalizedRanges()).willReturn(new NormalizedRangesDto());
        mockMvc.perform(get("/api/cryptos/normalized").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getMaxNormalizedRangeForDay() throws Exception {
        given(cryptoService.getMaxNormalizedRangeForDay(any())).willReturn(new NormalizedRangeDto());
        mockMvc.perform(get("/api/cryptos/normalized/max").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getCryptosByNameMax() throws Exception {
        given(cryptoService.getMaxByCrypto(any(), any(), any())).willReturn(new CryptoPriceDto());
        mockMvc.perform(get("/api/cryptos/BTC/max").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getCryptosByNameMin() throws Exception {
        given(cryptoService.getMinByCrypto(any(), any(), any())).willReturn(new CryptoPriceDto());
        mockMvc.perform(get("/api/cryptos/BTC/min").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getCryptosByNameOldest() throws Exception {
        given(cryptoService.getOldestByCrypto(any(), any(), any())).willReturn(new CryptoPriceDto());
        mockMvc.perform(get("/api/cryptos/BTC/oldest").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getCryptosByNameNewest() throws Exception {
        given(cryptoService.getNewestByCrypto(any(), any(), any())).willReturn(new CryptoPriceDto());
        mockMvc.perform(get("/api/cryptos/BTC/newest").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}