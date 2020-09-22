package com.man.util;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理
 *
 */
public class DateUtils {
    /** 时间格式(yyyy-MM-dd) */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /** 时间格式(yyyy-MM-dd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @return  返回yyyy-MM-dd格式日期
     */
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @param pattern  格式，如：DateUtils.DATE_TIME_PATTERN
     * @return  返回yyyy-MM-dd格式日期
     */
    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
    public static String defaultFormat(Date date) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(DateUtils.DATE_TIME_PATTERN);
            return df.format(date);
        }
        return null;
    }
    /**
     * 字符串转换成日期
     * @param strDate 日期字符串
     * @param pattern 日期的格式，如：DateUtils.DATE_TIME_PATTERN
     */
    public static Date stringToDate(String strDate, String pattern) {
        if (StringUtils.isBlank(strDate)){
            return null;
        }
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 根据周数，获取开始日期、结束日期
     * @param week  周期  0本周，-1上周，-2上上周，1下周，2下下周
     * @return  返回date[0]开始日期、date[1]结束日期
     */
    /*public static Date[] getWeekStartAndEnd(int week) {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate(dateTime.plusWeeks(week));

        date = date.dayOfWeek().withMinimumValue();
        Date beginDate = date.toDate();
        Date endDate = date.plusDays(6).toDate();
        return new Date[]{beginDate, endDate};
    }*/

    /**
     * 对日期的【秒】进行加/减
     *
     * @param date 日期
     * @param seconds 秒数，负数为减
     * @return 加/减几秒后的日期
     */
    /*public static Date addDateSeconds(Date date, int seconds) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusSeconds(seconds).toDate();
    }
*/
    /**
     * 对日期的【分钟】进行加/减
     *
     * @param date 日期
     * @param minutes 分钟数，负数为减
     * @return 加/减几分钟后的日期
     */
    public static Date addDateMinutes(Date date, long minutes) {
        LocalDateTime local =  date2localdateTime(date);
        return localdateTime2date(local.plusMinutes(minutes));
    }

    /**
     * 对日期的【小时】进行加/减
     *
     * @param date 日期
     * @param hours 小时数，负数为减
     * @return 加/减几小时后的日期
     */
    /*public static Date addDateHours(Date date, int hours) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusHours(hours).toDate();
    }*/

    /**
     * 对日期的【天】进行加/减
     *
     * @param date 日期
     * @param days 天数，负数为减
     * @return 加/减几天后的日期
     */
    public static Date addDateDays(Date date, int days) {
        LocalDateTime local =  date2localdateTime(date);
        return localdateTime2date(local.plusDays(days));
    }

    /**
     * 对日期的【周】进行加/减
     *
     * @param date 日期
     * @param weeks 周数，负数为减
     * @return 加/减几周后的日期
     */
    public static Date addDateWeeks(Date date, int weeks) {
        LocalDateTime local =  date2localdateTime(date);
        return localdateTime2date(local.plusWeeks(weeks));
    }

    /**
     * 对日期的【月】进行加/减
     *
     * @param date 日期
     * @param months 月数，负数为减
     * @return 加/减几月后的日期
     */
    public static Date addDateMonths(Date date, int months) {
        LocalDateTime local =  date2localdateTime(date);
        return localdateTime2date(local.plusMonths(months));
    }

    /**
     * 对日期的【年】进行加/减
     *
     * @param date 日期
     * @param years 年数，负数为减
     * @return 加/减几年后的日期
     */
    public static Date addDateYears(Date date, int years) {
        LocalDateTime local =  date2localdateTime(date);
        return localdateTime2date(local.plusYears(years));
    }
    /**
     * 判断 oldDate 是否在 newDate 的 days 天外
     * @param oldDate
     * @param newDate
     * @param days
     * @return
     */
    public static boolean before(Date oldDate ,Date  newDate ,int days){
        LocalDate olddate = date2localdate(oldDate);
        LocalDate newdate = date2localdate(newDate);
        LocalDate tempdate = date2localdate(newDate).minusDays(days);
        return !olddate.isAfter(tempdate) && !olddate.isBefore(newdate);
    }

    public static LocalDate date2localdate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    public static LocalDateTime date2localdateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Date localdate2date(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }
    public static Date localdateTime2date(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zone);
        return  Date.from(zdt.toInstant());
    }
    public static int rangeDays(LocalDate end,LocalDate start ) {
        return (int)Math.abs(end.until(start, ChronoUnit.DAYS));
    }
    public static int rangeMonths(LocalDate end,LocalDate start ) {
        return (int)Math.abs(end.until(start, ChronoUnit.MONTHS));
    }
    public static int rangeMinutes(LocalDate end,LocalDate start) {
        return (int)Math.abs(end.until(start,ChronoUnit.MINUTES));
    }
     
    public static long todayAddOf(int days,int hour,int minute,int second) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, days);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.MILLISECOND, 0);
        cal.getTimeInMillis();
        return cal.getTimeInMillis();
    }
    public static int rangeYears(Date start,Date end) {
        return (int)Math.abs(end.getYear()-start.getYear());
    }

    /**
     * 获取当天零点时间
     *
     * @return
     */
    public static Date getTodayStart(){
        LocalDateTime todayStart = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        return localdateTime2date(todayStart);
    }

    /**
     * 根据日期计算剩余分钟数
     * @param time
     *
     */
    public static long getDurationToMinutes(LocalDateTime time,LocalDateTime time1){
        Duration duration  = Duration.between(time, time1 );
        return duration.toMinutes();
    }
    /**
     * 根据日期计算时间差
     * @param time
     *
     */
    public static long getDurationToDays(LocalDateTime time,LocalDateTime time1){
        Duration duration  = Duration.between(time, time1 );
        return duration.toDays();
    }

}
