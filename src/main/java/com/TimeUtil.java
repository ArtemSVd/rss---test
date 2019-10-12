package com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TimeUtil {

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }
    public static Date parseDate(String pubDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        SimpleDateFormat format1 = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        try {
            return format.parse(pubDate);
        } catch (ParseException e) {
            return format1.parse(pubDate);
        }
    }
}
