package com.PostInventory.Utlis;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeFormat {

    static DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;

    public static String dateTimeFormat(){
        return formatter.format(LocalDate.now());
    }
}
