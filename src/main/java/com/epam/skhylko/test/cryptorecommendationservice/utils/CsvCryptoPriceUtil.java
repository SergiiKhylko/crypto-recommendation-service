package com.epam.skhylko.test.cryptorecommendationservice.utils;

import com.epam.skhylko.test.cryptorecommendationservice.entities.CryptoPrice;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

@Component
public class CsvCryptoPriceUtil {

    private static final String CRYPTO_PRICES_DIR = "prices";
    private static final String CRYPTO_PRICES_FILE_MASK_END = "_values.csv";

    private static final Logger LOG = LoggerFactory.getLogger(CsvCryptoPriceUtil.class);

    /**
     * process all csv files from the directory
     * @param processor
     * @throws RuntimeException if directory does not exist
     */
    public void processCsvFiles(CsvCryptoProcessor processor) {
        Arrays.stream(getCryptoPricesFilesFromDir(getCryptoPricesDir()))
                .forEach(csv -> processCsv(csv, processor));
    }

    /**
     * process csv file
     * save all records from csv file to database
     *
     * @param csv
     * @param processor
     * @throws FileNotFoundException if csv file does not exist
     * @throws RuntimeException if csv file is not valid
     */
    public void processCsv(File csv, CsvCryptoProcessor processor) {
         try {
            CsvToBean<CryptoPrice> csvToBean = new CsvToBeanBuilder<CryptoPrice>(new FileReader(csv))
                    .withType(CryptoPrice.class)
                    .build();

            for (CryptoPrice cryptoPrice : csvToBean) {
                processor.process(cryptoPrice);
            }

        } catch (FileNotFoundException e) {
             LOG.error(String.format("File '%s' not found", csv.getName()), e);
        } catch (RuntimeException e) {
             LOG.error(String.format("Error during parsing csv file '%s'. File is not valid.", csv.getName()), e);
        }
    }

    /**
     * process get all csv files from directory
     * @throws RuntimeException if directory does not exist
     */
    public File[] getCryptoPricesFilesFromDir(File cryptoPricesDir){

        if (!cryptoPricesDir.isDirectory()) {
            throw new RuntimeException("Directory is not exist");
        }

        return cryptoPricesDir
                .listFiles(file -> file.isFile() && file.getName().endsWith(CRYPTO_PRICES_FILE_MASK_END));
    }

    private File getCryptoPricesDir() {
        return new File(CRYPTO_PRICES_DIR);
    }
}
