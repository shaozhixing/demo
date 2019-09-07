package com.example.demo.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author: shaozhixing
 * @date: 2019/7/29 10:41
 * @copyright: gofun
 */
public class DateUtil {

    public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
    public static final String YYYYMMDD = "yyyy-MM-dd";

    /**
     * 获取当前时间戳
     * @return
     */
    public static int getCurrentTimestamp() {
        return (int) (System.currentTimeMillis() / 1000L);
    }

    /**
     * 获取当前String类型时间
     * @return
     */
    public static String getCurrentTimeString() {
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localTime = LocalDateTime.ofInstant(Instant.now(), zone);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(YYYYMMDDHHMMSS);
        return dateTimeFormatter.format(localTime);
    }

    /**
     * 获得当前Date类型时间
     * @return
     */
    public static Date getCurrentTimeDate() {
        return new Date();
    }

    /**
     * 获得指定格式的当前时间
     * @param pattern
     * @return
     */
    public static String getStringNow(String pattern) {
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localTime = LocalDateTime.ofInstant(Instant.now(), zone);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(localTime);
    }

    public static <T> T getTime (String date, Class<T> clazz) {
        Object obj = null;
        try {
            T t = clazz.newInstance();
            if (t instanceof String) {
                obj = date;
                return (T) obj;
            } else if (t instanceof Date) {
                SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDDHHMMSS);
                ParsePosition pos = new ParsePosition(0);
                obj = formatter.parse(date, pos);
            } else if (t instanceof Integer) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYYMMDDHHMMSS);
                Date da;
                da = simpleDateFormat.parse(date);
                long ts = da.getTime();
                obj = String.valueOf(ts/1000L);
            }
        } catch (Exception e) {
            obj = null;
        }
        return (T) obj;
    }

}
