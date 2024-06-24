package org.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataUtils {
    private static final DateTimeFormatter formatterY = DateTimeFormatter.ofPattern("yyyy");

    public static String makeFormat(String date, String month, String year) {
        if (date.length() == 1) date = '0' + date;
        if (month.length() == 1) month = '0' + month;
        return String.format("%s/%s/%s", month, date, year);
    }

    public static int searchLeapYear() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        int year = Integer.parseInt(currentDateTime.format(formatterY));
        if (year % 4 != 0) {
            year += year % 4;
        }
        return year;
    }

    public static boolean isLessTwoPercent(String need, String have) {
        int needed = Integer.parseInt(need);
        int had = Integer.parseInt(have);
        return needed * 1.2 > had && needed * 0.8 < had;
    }
}
