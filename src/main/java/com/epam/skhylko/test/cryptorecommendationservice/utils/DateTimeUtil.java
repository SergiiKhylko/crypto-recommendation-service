package com.epam.skhylko.test.cryptorecommendationservice.utils;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Date Time Util component
 */
@Component
public class DateTimeUtil {

    /**
     * converts localDate to timestamp
     * @param localDate
     * @param offset - system TimeZone
     */
    public Long localDateToTimestamp(LocalDate localDate, ZoneOffset offset) {
        return localDate.atStartOfDay().toInstant(offset).toEpochMilli();
    }

    /**
     * converts localDate to timestamp
     * @param timestamp - timestamp in milliseconds
     * @param offset - system TimeZone
     */
    public LocalDateTime timestampToLocalDateTime(Long timestamp, ZoneOffset offset) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), offset);
    }

    /**
     * get start of current month
     */
    public LocalDate getStartOfCurrentMonth() {
        return LocalDate.now().withDayOfMonth(1);
    }

    /**
     * get start of current month in timestamp format
     * @param offset - system TimeZone
     */
    public Long getStartOfCurrentMonthTimestamp(ZoneOffset offset) {
        return localDateToTimestamp(getStartOfCurrentMonth(), offset);
    }

    /**
     * get current timestamp
     * @param offset - system TimeZone
     */
    public Long getCurrentTimestamp(ZoneOffset offset) {
        return LocalDateTime.now().toInstant(offset).toEpochMilli();
    }

    /**
     * get today timestamp
     * @param offset - system TimeZone
     */
    public Long getTodayTimestamp(ZoneOffset offset) {
        return localDateToTimestamp(LocalDate.now(), offset);
    }

    /**
     * get end day Timestamp
     * @param offset - system TimeZone
     */
    public Long getEndDayTimestamp(LocalDate localDate, ZoneOffset offset) {
        return localDateToTimestamp(localDate.plusDays(1), offset);
    }
}
