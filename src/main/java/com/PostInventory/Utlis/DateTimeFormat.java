package com.PostInventory.Utlis;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeFormat {

    static DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    static DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public static String dateTimeFormat(){
        return formatter.format(LocalDate.now());
    }

    public static Date getZonedDateTime(){
        OffsetDateTime utc = OffsetDateTime.now(ZoneOffset.UTC);
//        Instant nowUtc = Instant.now();
//        ZoneId europeWarsaw = ZoneId.of("Europe/Warsaw");
//        return ZonedDateTime.ofInstant(nowUtc, europeWarsaw);
        Date date = Date.from(utc.toInstant());
        return date;
    }
}
