package com.epam.skhylko.test.cryptorecommendationservice.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class CsvCryptoPriceUtilTest {

    CsvCryptoPriceUtil util;

    @BeforeEach
    void setUp() {
        util = new CsvCryptoPriceUtil();
    }


    @Test
    void getCryptoPricesFilesDirNotExist(@Mock File mockFile) {
        when(mockFile.isDirectory()).thenReturn(false);
        Exception exception = assertThrows(RuntimeException.class, () -> util.getCryptoPricesFilesFromDir(mockFile) );

        String expectedMessage = "Directory is not exist";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}