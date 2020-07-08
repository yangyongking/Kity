package me.windleafy.kity.java.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by JOUAV on 2015/12/23.
 */
public class DateKit {

    private DateKit() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static String date() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new java.util.Date());
    }

    public static String time() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new java.util.Date());
    }
}
