package com.epam.skhylko.test.cryptorecommendationservice.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeUtilTest {

    DateTimeUtil dateTimeUtil;

    @BeforeEach
    void setUp() {
        dateTimeUtil = new DateTimeUtil();
    }

    @Test
    void localDateToTimestamp() {
        LocalDate date = LocalDate.of(2022, 11, 8);
        Long timestamp = 1667865600000L;
        assertEquals(timestamp, dateTimeUtil.localDateToTimestamp(date, ZoneOffset.UTC));
    }

    @Test
    void timestampToLocalDateTime() {
        Long timestamp = 1667865600000L;
        LocalDateTime time = LocalDateTime.of(2022, 11, 8, 0,0,0);
        assertEquals(time, dateTimeUtil.timestampToLocalDateTime(timestamp, ZoneOffset.UTC));
    }

    @Test
    void getStartOfCurrentMonth() {
        LocalDate startDay = LocalDate.now().withDayOfMonth(1);
        assertEquals(startDay, dateTimeUtil.getStartOfCurrentMonth());
    }

    @Test
    void getStartOfCurrentMonthTimestamp() {
        Long startDay = LocalDate.now().withDayOfMonth(1).atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();
        assertEquals(startDay, dateTimeUtil.getStartOfCurrentMonthTimestamp(ZoneOffset.UTC));
    }

    @Test
    void getTodayTimestamp() {
        assertEquals(LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli(), dateTimeUtil.getTodayTimestamp(ZoneOffset.UTC));

    }

    @Test
    void getEndDayTimestamp() {
        LocalDate date = LocalDate.of(2022, 11, 8);
        assertEquals(date.plusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli(), dateTimeUtil.getEndDayTimestamp(date, ZoneOffset.UTC));

    }
}