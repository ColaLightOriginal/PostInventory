package com.PostInventory.Utlis;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeFormat {

    public static Date getCentralTime(){
        OffsetDateTime utc = OffsetDateTime.now(ZoneOffset.UTC);
//        Instant nowUtc = Instant.now();
//        ZoneId europeWarsaw = ZoneId.of("Europe/Warsaw");
//        return ZonedDateTime.ofInstant(nowUtc, europeWarsaw);
        Date date = Date.from(utc.toInstant());
        return date;
    }
}
