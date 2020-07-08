package me.windleafy.kity.java.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import me.windleafy.kity.java.judge.JudgeKit;

/**
 * 取得系统时间
 * 1。
 * long currentTime=System.currentTimeMillis();
 * <p/>
 * 2。
 * final Calendar mCalendar=Calendar.getInstance();
 * mCalendar.setTimeInMillis(currentTime);
 * 取得小时：mHour=mCalendar.get(Calendar.HOUR);
 * 取得分钟：mMinuts=mCalendar.get(Calendar.MINUTE);
 * <p/>
 * <p/>
 * 3。
 * Time t=new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料
 * t.setToNow(); // 取得系统时间。
 * int year = t.year;
 * int month = t.month;
 * int date = t.monthDay;
 * int currentHour = t.currentHour;    // 0-23
 * <p/>
 * 4。
 * DateFormat df = new SimpleDateFormat("HH:mm:ss");
 * df.format(new Date());
 */
public class TimeKit {

    private TimeKit() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }


    public static final String FormatDefault = "yyyy-MM-dd HH:mm:ss";
    public static final String FormatDate = "yyyy-MM-dd";
    public static final String FormatDateMonthDay = "MM-dd";
    public static final String FormatTime = "HH:mm:ss";
    public static final String FormatTimeHourMinute = "HH:mm";
    public static final String FormatYear = "yyyy";
    public static final String FormatMonth = "MM";
    public static final String FormatDay = "dd";
    public static final String FormatHour = "HH";
    public static final String FormatMinute = "mm";
    public static final String FormatSecond = "ss";

    public static final String FormatSingleMonth = "M";
    public static final String FormatSingleDay = "d";


    public static final String DateTimeSeparator = " ";
    public static final String DateSeparator = "-";
    public static final String TimeSeparator = ":";


    /**
     * 获取Date
     *
     * @param calendar
     * @return
     */
    public static Date getDate(Calendar calendar) {
        return calendar.getTime();
    }

    /**
     * 获取Calendar
     *
     * @param date
     * @return
     */
    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    /**
     * 获取字符串时间
     *
     * @param timeString 时间字符串，例如："2018-01-01 12:34:56"
     * @param timeFormat 例如："yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static Date getDate(String timeString, String timeFormat) throws ParseException, NullPointerException {
        if (timeString == null) {
            throw new NullPointerException();
        }
        SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
        return sdf.parse(timeString);
    }

    /**
     * 获取字符串时间
     *
     * @param timeString 时间字符串，形如"yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static Date getDate(String timeString) throws ParseException, NullPointerException {
        return getDate(timeString, FormatDefault);
    }


    /**
     * 获取字符串时间
     *
     * @param timeString 时间字符串，例如："2018-01-01 12:34:56"
     * @param timeFormat 例如："yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static Calendar getCalendar(String timeString, String timeFormat) throws ParseException, NullPointerException {
        return getCalendar(getDate(timeString, timeFormat));
    }

    /**
     * 获取字符串时间
     *
     * @param timeString 时间字符串，形如"yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static Calendar getCalendar(String timeString) throws ParseException, NullPointerException {
        return getCalendar(timeString, FormatDefault);
    }


    /**
     * 获取自定义格式化时间
     *
     * @param timeString
     * @param timeFormat
     * @param targetFormat
     * @return
     */
    public static String getString(String timeString, String timeFormat, String targetFormat) {
        try {
            return getString(getDate(timeString, timeFormat), targetFormat);
        } catch (ParseException e) {
            return "";
        } catch (NullPointerException e) {
            return "";
        }
    }

    /**
     * 获取自定义格式化时间
     *
     * @param timeString
     * @param targetFormat
     * @return
     */
    public static String getString(String timeString, String targetFormat) {
        return getString(timeString, FormatDefault, targetFormat);
    }

    /**
     * 获取自定义格式化时间
     *
     * @param date
     * @param targetFormat
     * @return
     */
    public static String getString(Date date, String targetFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(targetFormat);
        return sdf.format(date);
    }

    /**
     * 获取自定义格式化时间
     *
     * @param calendar
     * @param targetFormat
     * @return
     */
    public static String getString(Calendar calendar, String targetFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(targetFormat);
        return sdf.format(calendar.getTime());
    }


    /**
     * @param date
     * @param targetFormatArrays 参数类型I：{@link TimeKit#FormatDefault}，
     *                           参数类型II：{@link TimeKit#FormatDate},{@link TimeKit#FormatTime},
     *                           {@link TimeKit#FormatDateMonthDay},{@link TimeKit#FormatTimeHourMinute}
     *                           参数类型III：{@link TimeKit#FormatYear},{@link TimeKit#FormatMonth},
     *                           {@link TimeKit#FormatDay},{@link TimeKit#FormatHour},
     *                           {@link TimeKit#FormatMinute},{@link TimeKit#FormatSecond}
     * @return
     */
    public static String getTime(Date date, String... targetFormatArrays) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatString(targetFormatArrays));
        return sdf.format(date);
    }

    /**
     * @param calendar
     * @param targetFormatArrays 参数类型I：{@link TimeKit#FormatDefault}，
     *                           参数类型II：{@link TimeKit#FormatDate},{@link TimeKit#FormatTime},
     *                           {@link TimeKit#FormatDateMonthDay},{@link TimeKit#FormatTimeHourMinute}
     *                           参数类型III：{@link TimeKit#FormatYear},{@link TimeKit#FormatMonth},
     *                           {@link TimeKit#FormatDay},{@link TimeKit#FormatHour},
     *                           {@link TimeKit#FormatMinute},{@link TimeKit#FormatSecond}
     * @return
     */
    public static String getTime(Calendar calendar, String... targetFormatArrays) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatString(targetFormatArrays));
        return sdf.format(calendar.getTime());
    }

    /**
     * @param timeString
     * @param timeFormat
     * @param targetFormatArrays 参数类型I：{@link TimeKit#FormatDefault}，
     *                           参数类型II：{@link TimeKit#FormatDate},{@link TimeKit#FormatTime},
     *                           {@link TimeKit#FormatDateMonthDay},{@link TimeKit#FormatTimeHourMinute}
     *                           参数类型III：{@link TimeKit#FormatYear},{@link TimeKit#FormatMonth},
     *                           {@link TimeKit#FormatDay},{@link TimeKit#FormatHour},
     *                           {@link TimeKit#FormatMinute},{@link TimeKit#FormatSecond}
     * @return
     */
    public static String getTime(String timeString, String timeFormat, String... targetFormatArrays) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatString(targetFormatArrays));
        try {
            return sdf.format(getDate(timeString, timeFormat));
        } catch (ParseException e) {
            return "";
        } catch (NullPointerException e) {
            return "";
        }
    }

    private static String formatString(String... targetFormatArrays) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < targetFormatArrays.length; i++) {
            String temp = targetFormatArrays[i];
            if (i > 0) {
                String last = targetFormatArrays[i - 1];
                if ((JudgeKit.contain(last, FormatDate, FormatDateMonthDay, FormatYear, FormatMonth, FormatDay)
                        && JudgeKit.contain(temp, FormatTime, FormatTimeHourMinute, FormatHour, FormatMinute, FormatSecond))) {
                    builder.append(DateTimeSeparator);
                }
                if ((JudgeKit.contain(last, FormatYear, FormatMonth, FormatDay)
                        && JudgeKit.contain(temp, FormatYear, FormatMonth, FormatDay))) {
                    builder.append(DateSeparator);
                }
                if ((JudgeKit.contain(last, FormatHour, FormatMinute, FormatSecond)
                        && JudgeKit.contain(temp, FormatHour, FormatMinute, FormatSecond))) {
                    builder.append(TimeSeparator);
                }
            }
            builder.append(temp);
        }
        return builder.toString();
    }


    /**
     * 获取时间，需要显示true，不需要显示false
     *
     * @param timeString
     * @param timeFormat
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param secnod
     * @return
     */
    public static String getTime(String timeString, String timeFormat, boolean year, boolean month, boolean day, boolean hour, boolean minute, boolean secnod) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatString(year, month, day, hour, minute, secnod));
        try {
            return sdf.format(getDate(timeString, timeFormat));
        } catch (ParseException e) {
            return "";
        } catch (NullPointerException e) {
            return "";
        }
    }

    /**
     * 获取时间，需要显示true，不需要显示false
     *
     * @param date
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param secnod
     * @return
     */
    public static String getTime(Date date, boolean year, boolean month, boolean day, boolean hour, boolean minute, boolean secnod) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatString(year, month, day, hour, minute, secnod));
        return sdf.format(date);
    }

    /**
     * 获取时间，需要显示true，不需要显示false
     *
     * @param calendar
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param secnod
     * @return
     */
    public static String getTime(Calendar calendar, boolean year, boolean month, boolean day, boolean hour, boolean minute, boolean secnod) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatString(year, month, day, hour, minute, secnod));
        return sdf.format(calendar.getTime());
    }

    private static String formatString(boolean year, boolean month, boolean day, boolean hour, boolean minute, boolean second) {
        StringBuilder builder = new StringBuilder();
        if (year) {
            builder.append(FormatYear);
        }
        if (month) {
            builder.append((year ? DateSeparator : "") + FormatMonth);
        }
        if (day) {
            builder.append((year || month ? DateSeparator : "") + FormatDay);
        }
        if (hour) {
            builder.append((year || month || day ? DateTimeSeparator : "") + FormatHour);
        }
        if (minute) {
            builder.append((hour ? TimeSeparator : "") + FormatMinute);
        }
        if (second) {
            builder.append((hour || minute ? TimeSeparator : "") + FormatSecond);
        }
        return builder.toString();
    }

    /**
     * 当前时间，年-月-日
     *
     * @return
     */
    public static String currentDate() {
        DateFormat df = new SimpleDateFormat(FormatDate);
        return df.format(new Date());
    }

    /**
     * 当前时间，年-月-日 时:分:秒
     *
     * @return
     */
    public static String currentDateTime() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new java.util.Date());
    }

    /**
     * 当前时间，时:分:秒:毫秒
     *
     * @return
     */
    public static String currentHourMinuteSecondMillis() {
        DateFormat df = new SimpleDateFormat("HH:mm:ss:SSS");
        return df.format(new Date());
    }

    /**
     * 当前时间，时:分:秒
     *
     * @return
     */
    public static String currentHourMinuteSecond() {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        return df.format(new Date());
    }

    /**
     * 当前时间，时:分
     *
     * @return
     */
    public static String currentHourMinute() {
        DateFormat df = new SimpleDateFormat("HH:mm");
        return df.format(new Date());
    }


    /**
     * 获取当前时间
     *
     * @param HH  时
     * @param mm  分
     * @param ss  秒
     * @param SSS 毫秒
     * @return
     */
    public static String currentTime(boolean HH, boolean mm, boolean ss, boolean SSS) {
        StringBuffer format = new StringBuffer();
        if (HH) {
            format.append("HH");
        }
        if (mm) {
            if (format.length() != 0)
                format.append(":");
            format.append("mm");
        }
        if (ss) {
            if (format.length() != 0)
                format.append(":");
            format.append("ss");
        }
        if (SSS) {
            if (format.length() != 0)
                format.append(":");
            format.append("SSS");
        }
        DateFormat df = new SimpleDateFormat(format.toString());
        return df.format(new Date());
    }

}
