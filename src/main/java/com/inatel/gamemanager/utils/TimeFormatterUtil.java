package com.inatel.gamemanager.utils;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeFormatterUtil {

    private TimeFormatterUtil(){
    }

    public static boolean isISODateFormat(String date){
        DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_DATE;

        try {
            isoFormatter.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
