package com.PostInventory.Utlis;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormat {

    static DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static String dateTimeFormat(){
        return formatter.format(LocalDate.now());
    }

    public static ZonedDateTime getZonedDateTime(){
        Instant nowUtc = Instant.now();
        ZoneId europeWarsaw = ZoneId.of("Europe/Warsaw");
        return ZonedDateTime.ofInstant(nowUtc, europeWarsaw);
    }
}
