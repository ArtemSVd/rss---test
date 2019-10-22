package com.aggregator.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Служебный класс предназначен для работы с датами.
 */
public final class TimeUtil {

    /**
     * Служебный класс, все методы статические.
     */
    private TimeUtil() {
    }

    /**
     * Метод необходим для подсчета разницы времени между двумя датами.
     * @param dateBefore дата предшествующая дате из второго аргумента
     * @param dateNow текущая дата
     * @param timeUnit параметр указывает в каких единицах
     *                 следует возвращать разницу между датами
     * @return возвращает разницу между датами
     * в зависимости от timeUnit
     */
    public static long getDateDiff(
            final Date dateBefore,
            final Date dateNow,
            final TimeUnit timeUnit) {

        long diffInMilliSeconds = dateNow.getTime() - dateBefore.getTime();
        return timeUnit.convert(diffInMilliSeconds, TimeUnit.MILLISECONDS);
    }

    /**
     * Метод для конвертации строки из RSS - лент, содержащей дату
     * конвертация происходит из двух наиболее распространенных форматов.
     * @param pubDate строка, содержащая дату
     * @return возвращает объект Date
     * @throws ParseException исключение связано с ошибкой конвертации,
     * выбрасывается, если формат даты из строки не соответствует заданным
     */
    public static Date parseDate(final String pubDate) throws ParseException {

        SimpleDateFormat firstFormat =
                new SimpleDateFormat(
                        "dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        SimpleDateFormat secondFormat =
                new SimpleDateFormat(
                        "E, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);

        try {
            return firstFormat.parse(pubDate);
        } catch (ParseException e) {
            return secondFormat.parse(pubDate);
        }
    }
}
