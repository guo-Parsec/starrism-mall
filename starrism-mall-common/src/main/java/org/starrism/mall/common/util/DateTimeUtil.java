package org.starrism.mall.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * <p>日期时间工具类</p>
 *
 * @author hedwing
 * @since 2022/9/17
 **/
public class DateTimeUtil {
    public static final String DEFAULT_DATETIME_PATTERN = "YYYY-MM-DD HH:mm:ss";

    public static DateTimeFormatter getDefaultDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(DEFAULT_DATETIME_PATTERN);
    }

    /**
     * <p>获取当前系统时间 格式为YYYY-MM-DD HH:mm:ss</p>
     *
     * @return java.lang.String
     * @author hedwing
     * @since 2022/9/17
     */
    public static String now() {
        return getDefaultDateTimeFormatter().format(LocalDateTime.now());
    }

    /**
     * <p>从字符串中格式化 格式为YYYY-MM-DD HH:mm:ss</p>
     *
     * @param time time
     * @return java.time.LocalDateTime
     * @author hedwing
     * @since 2022/9/17
     */
    public static LocalDateTime parse(String time) {
        return LocalDateTime.parse(time, getDefaultDateTimeFormatter());
    }

    /**
     * <p>日期比较大小</p>
     *
     * @param localDateTime        localDateTime
     * @param compareLocalDateTime compareLocalDateTime
     * @return int
     * @author hedwing
     * @since 2022/9/17
     */
    public static int compareTo(LocalDateTime localDateTime, LocalDateTime compareLocalDateTime) {
        return localDateTime.compareTo(compareLocalDateTime);
    }

    /**
     * <p>日期比较大小</p>
     *
     * @param localDateTime        localDateTime
     * @param compareLocalDateTime compareLocalDateTime
     * @return int
     * @author hedwing
     * @since 2022/9/17
     */
    public static int compareTo(String localDateTime, String compareLocalDateTime) {
        return parse(localDateTime).compareTo(parse(compareLocalDateTime));
    }

    /**
     * <p>日期比较大小</p>
     *
     * @param localDateTime        localDateTime
     * @param compareLocalDateTime compareLocalDateTime
     * @return int
     * @author hedwing
     * @since 2022/9/17
     */
    public static int compareTo(String localDateTime, LocalDateTime compareLocalDateTime) {
        return parse(localDateTime).compareTo(compareLocalDateTime);
    }

    /**
     * <p>同当前日期比较大小</p>
     *
     * @param localDateTime localDateTime
     * @return int
     * @author hedwing
     * @since 2022/9/17
     */
    public static int compareToNow(LocalDateTime localDateTime) {
        return compareTo(localDateTime, LocalDateTime.now());
    }

    /**
     * <p>同当前日期比较大小</p>
     *
     * @param localDateTime localDateTime
     * @return int
     * @author hedwing
     * @since 2022/9/17
     */
    public static int compareToNow(String localDateTime) {
        return compareTo(localDateTime, LocalDateTime.now());
    }

    /**
     * <p>计算 localDateTime 与 compareLocalDateTime 间隔时间</p>
     * <p>如果 localDateTime > compareLocalDateTime 则输出正数否则输出负数</p>
     *
     * @param localDateTime        日期1
     * @param compareLocalDateTime 被比较日期
     * @param chronoUnit           间隔单位
     * @return long
     * @author hedwing
     * @since 2022/9/17
     */
    public static long between(LocalDateTime localDateTime, LocalDateTime compareLocalDateTime, ChronoUnit chronoUnit) {
        return localDateTime.until(compareLocalDateTime, chronoUnit);
    }

    /**
     * <p>计算 localDateTime 与 compareLocalDateTime 间隔秒数</p>
     * <p>如果 localDateTime > compareLocalDateTime 则输出正数否则输出负数</p>
     *
     * @param localDateTime        日期1
     * @param compareLocalDateTime 被比较日期
     * @return long
     * @author hedwing
     * @since 2022/9/17
     */
    public static long between(LocalDateTime localDateTime, LocalDateTime compareLocalDateTime) {
        return localDateTime.until(compareLocalDateTime, ChronoUnit.SECONDS);
    }

    /**
     * <p>计算 localDateTime 与 compareLocalDateTime 间隔时间</p>
     * <p>如果 localDateTime > compareLocalDateTime 则输出正数否则输出负数</p>
     *
     * @param localDateTime        日期1
     * @param compareLocalDateTime 被比较日期
     * @param chronoUnit           间隔单位
     * @return long
     * @author hedwing
     * @since 2022/9/17
     */
    public static long between(String localDateTime, String compareLocalDateTime, ChronoUnit chronoUnit) {
        return parse(localDateTime).until(parse(compareLocalDateTime), chronoUnit);
    }

    /**
     * <p>计算 localDateTime 与 compareLocalDateTime 间隔秒数</p>
     * <p>如果 localDateTime > compareLocalDateTime 则输出正数否则输出负数</p>
     *
     * @param localDateTime        日期1
     * @param compareLocalDateTime 被比较日期
     * @return long
     * @author hedwing
     * @since 2022/9/17
     */
    public static long between(String localDateTime, String compareLocalDateTime) {
        return parse(localDateTime).until(parse(compareLocalDateTime), ChronoUnit.SECONDS);
    }
}
