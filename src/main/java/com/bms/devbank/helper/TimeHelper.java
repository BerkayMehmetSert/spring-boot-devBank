package com.bms.devbank.helper;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimeHelper {
    public static LocalDate startDate() {
        return LocalDate.now();
    }

    public static LocalDate endDate(int day) {
        return LocalDate.now().plusDays(day);
    }
    public static LocalDateTime localDateTime() {
        return LocalDateTime.now();
    }
}
