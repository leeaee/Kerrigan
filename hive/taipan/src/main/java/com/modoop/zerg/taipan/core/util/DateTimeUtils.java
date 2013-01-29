package com.modoop.zerg.taipan.core.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author: Genkyo Lee
 * @date: 1/14/13
 */
public class DateTimeUtils
{

    // Properties
    /**
     * 当一个时间戳为0时，代表1970-1-1 00:00:00，因此，需要有一个特殊的值来表示 null 值的时间戳。
     */
    public static final long TIME_OF_NA = 111111111111L;

    private static final String longFormat = "yyyy-MM-dd HH:mm:ss";

    private static final String dateFormat = "yyyy-MM-dd";

    private static final String shortDateFormat = "yyyyMMdd";

    private static final String weekDayFormat = "E";

    private static final String ymFormat = "yyyyMM";

    private static final long MILLISEC_OF_DAY = 86400000L;

    private static final int DAY_OF_YEAR = 365;

    // Constructor
    private DateTimeUtils()
    {
    }

    // Methods

    /**
     * Transform milliseccond timestamp into the date-time form of '<TT>2004-06-19 23:21:58</TT>'. If the {@code
     * timestamp} equals {@link TIME_OF_NA}, '<TT>--</TT>' would be returned.
     *
     * @param timestamp the milliseconds.
     * @return String of the time.
     */
    public static DateTime longToDateTime(long timestamp)
    {
        return new DateTime(timestamp);
    }

    /**
     * Transform milliseccond timestamp into the date-time form of '<TT>2004-06-19 23:21:58</TT>'. If the {@code
     * timestamp} equals {@link TIME_OF_NA}, '<TT>--</TT>' would be returned.
     *
     * @param timestamp the milliseconds.
     * @return String of the time.
     */
    public static String longToDateTimeString(long timestamp)
    {
        if (timestamp == TIME_OF_NA)
        {
            return "--";
        }

        return new DateTime(timestamp).toString(longFormat);
    }

    /**
     * Transform milliseccond timestamp into the date form of '<TT>2004-06-19</TT>'. If the {@code timestamp} equals
     * {@link TIME_OF_NA}, '<TT>--</TT>' would be returned.
     *
     * @param timestamp the milliseconds.
     * @return String of the time.
     */
    public static String longToDateString(long timestamp)
    {
        if (timestamp == TIME_OF_NA)
        {
            return "--";
        }

        return new DateTime(timestamp).toString(dateFormat);
    }

    /**
     * Transform milliseccond timestamp into the date form of '<TT>20040619</TT>'. If the {@code timestamp} equals
     * {@link TIME_OF_NA}, '<TT>--</TT>' would be returned.
     *
     * @param timestamp the milliseconds.
     * @return String of the time.
     */
    public static String longToShortDateString(long timestamp)
    {
        if (timestamp == TIME_OF_NA)
        {
            return "--";
        }

        return new DateTime(timestamp).toString(shortDateFormat);
    }

    /**
     * Transform milliseccond timestamp into the date form of '<TT>200406</TT>'. If the {@code timestamp} equals
     * {@link TIME_OF_NA}, '<TT>--</TT>' would be returned.
     *
     * @param timestamp the milliseconds.
     * @return String of the time.
     */
    public static String longToYearMonth(long timestamp)
    {
        if (timestamp == TIME_OF_NA)
        {
            return "--";
        }

        return new DateTime(timestamp).toString(ymFormat);
    }

    /**
     * Get week day of specified timestamp.
     */
    public static String getWeekday(long timestamp)
    {
        if (timestamp == TIME_OF_NA)
        {
            return "--";
        }

        return new DateTime(timestamp).toString(weekDayFormat);
    }

    /**
     * Get the days difference between two timestamp.
     *
     * @return the days counted in integer.
     */
    public static int getDaysDiff(long time1, long time2)
    {
        long timeDiff = time1 >= time2 ? time1 - time2 : time2 - time1;

        if (timeDiff == 0)
        {
            return 0;
        }

        return (int) (timeDiff / MILLISEC_OF_DAY);
    }

    /**
     * Get the years difference between two timestamp.
     *
     * @return the years counted in integer.
     */
    public static int getYearsDiff(long time1, long time2)
    {
        long timeDiff = time1 >= time2 ? time1 - time2 : time2 - time1;

        if (timeDiff == 0)
        {
            return 0;
        }

        return (int) (timeDiff / (MILLISEC_OF_DAY * DAY_OF_YEAR));
    }

    /**
     * Parse a millisecond time by specified {@code year, month and day}.
     *
     * @param year  the year
     * @param month the month
     * @param day   the day
     * @return the timestamp on 00:00:00 of the date specified.
     */
    public static long getTime(String year, String month, String day)
    {
        if (Strings.isEmpty(year))
        {
            return TIME_OF_NA;
        }
        else
        {
            DateTime dateTime = new DateTime(Integer.parseInt(year), (Strings.isEmpty(month) ? 0 : Integer.parseInt(month)), (Strings.isEmpty(day) ? 0 : Integer.parseInt(day)), 0, 0);

            return dateTime.getMillis();
        }
    }

    private static Calendar getCalendarOfYearMonth(String yearMonth)
    {
        if (yearMonth == null || yearMonth.length() != 6)
        {
            return null;
        }

        int year;
        int month;

        try
        {
            year = Integer.parseInt(yearMonth.substring(0, 4));
            month = Integer.parseInt(yearMonth.substring(4));
        }
        catch (NumberFormatException e)
        {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    /**
     * Get the start timestamp of specified year-month.
     *
     * @param yearMonth the year-month in string.
     * @return the millisecond timestamp, and {@link TIME_OF_NA} when specified year-month is invalid.
     */
    public static long getStartTimeOfMonth(String yearMonth)
    {
        Calendar cal = getCalendarOfYearMonth(yearMonth);
        if (cal == null)
        {
            return TIME_OF_NA;
        }
        else
        {
            return cal.getTimeInMillis();
        }
    }

    /**
     * Get the end timestamp of specified year-month.
     *
     * @param yearMonth the year-month in string.
     * @return the millisecond timestamp, and {@link TIME_OF_NA} when specified year-month is invalid.
     */
    public static long getEndTimeOfMonth(String yearMonth)
    {
        Calendar cal = getCalendarOfYearMonth(yearMonth);
        if (cal == null)
        {
            return TIME_OF_NA;
        }
        else
        {
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 999);
            cal.add(Calendar.DAY_OF_YEAR, -1);

            return cal.getTimeInMillis();
        }
    }

    /**
     * Get the start birth time of specified age.
     *
     * @param ageStart the age, larger than 0.
     * @return the timestamp in long.
     */
    public static long getStartBirthTimeOfAge(int ageStart)
    {
        if (ageStart < 0)
        {
            return -1L;
        }

        String yearMonth = getYearMonthOfAge(ageStart);

        return getStartTimeOfMonth(yearMonth);
    }

    /**
     * Get the year month in form of 'yyyyMM' for specified age.
     *
     * @param age the age in int.
     * @return string of year month
     */
    public static String getYearMonthOfAge(int age)
    {
        Calendar cal = Calendar.getInstance();
        int curYear = cal.get(Calendar.YEAR);
        int curMonth = cal.get(Calendar.MONTH) + 1;

        StringBuffer yearMonth = new StringBuffer(String.valueOf(curYear - age));
        if (curMonth < 10)
        {
            yearMonth.append("0");
        }
        yearMonth.append(curMonth);
        return yearMonth.toString();
    }

    /**
     * Get the end birth time of specified age.
     *
     * @param ageEnd the age, larger than 0.
     * @return the timestamp in long.
     */
    public static long getEndBirthTimeOfAge(int ageEnd)
    {
        if (ageEnd < 0)
        {
            return -1L;
        }

        String yearMonth = getYearMonthOfAge(ageEnd);

        return getEndTimeOfMonth(yearMonth);
    }

    /**
     * get the months of latest half a year.The key is YYYYMM, the display value is YYYY-MM.
     *
     * @return map
     */
    public static Map<String, String> getHalfYearMonths()
    {
        Calendar cal = Calendar.getInstance();

        // 获得最近半年时间月份，以供用户选择查询
        Map<String, String> map = new TreeMap<String, String>();
        map.put(DateTimeUtils.longToYearMonth(cal.getTimeInMillis()), getDate(cal.getTimeInMillis()));
        for (int i = 1; i <= 5; i++)
        {
            cal.add(Calendar.MONTH, -1);
            map.put(DateTimeUtils.longToYearMonth(cal.getTimeInMillis()), getDate(cal.getTimeInMillis()));
        }
        return map;
    }

    /**
     * 日期显示格式转换.例如2005-08
     *
     * @param timestamp long时间戳
     * @return string 日期
     */
    public static String getDate(long timestamp)
    {
        StringBuffer sb = new StringBuffer(longToYearMonth(timestamp));
        sb.insert(4, '-');
        return sb.toString();
    }

    /**
     * 得到前一天的起始时间
     *
     * @param cal
     * @return Date
     */
    public static Date getBeginOfDay(Calendar cal)
    {
        Calendar cald = Calendar.getInstance();
        cald.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
        cald.set(Calendar.MILLISECOND, 0);
        return cald.getTime();
    }

    /**
     * 得到前一天的结束时间
     *
     * @param cal
     * @return Date
     */
    public static Date getEndOfDay(Calendar cal)
    {
        Calendar cald = Calendar.getInstance();
        cald.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 23, 59, 59);
        cald.set(Calendar.MILLISECOND, 999);
        return cald.getTime();
    }

    /**
     * 得到当前时间小时数
     *
     * @param cal
     * @return a hour of current time in int
     */
    public static int getCurrentHour(Calendar cal)
    {
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 把一个字符串时间转成long型
     *
     * @param dateString
     * @return long
     */
    public static long getStrToLongTime(String dateString)
    {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dateTime = fmt.parseDateTime(dateString);
        return dateTime.getMillis();
    }

    public static void main(String[] args)
    {
        System.out.println(DateTimeUtils.longToDateTime(0));
    }
}
