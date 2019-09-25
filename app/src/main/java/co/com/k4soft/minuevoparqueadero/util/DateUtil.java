package co.com.k4soft.minuevoparqueadero.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public static String DATE_FORMAT_HORA = "yyyy-MM-dd HH:mm:ss";

    public static String getCurrenDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_HORA, Locale.ENGLISH);
        Date date = new Date();
        return dateFormat.format(date);
    }
}
