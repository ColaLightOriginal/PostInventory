package com.PostInventory.Utlis;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeFormat {

    static DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static String dateTimeFormat(){
        return formatter.format(LocalDate.now());
    }
}
