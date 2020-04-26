package platform.zframe.common.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtils {
    //将LocalDateTime转为自定义的时间格式的字符串
    public static String getDateTimeAsString(LocalDateTime localDateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(formatter);
    }
    //将long类型的timestamp转为LocalDateTime
    public static LocalDateTime getDateTimeOfTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }
    //将long类型的timestamp转为LocalDateTime
    public static long getTimestampOfDateTime(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }
    //将某时间字符串转为自定义时间格式的LocalDateTime
    public static LocalDateTime parseStringToDateTime(String time, String format) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(time, df);
    }
    // long Timestamp 转 自定义时间格式
    public static String getTimeStampAsString(long timestamp,String format){
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern(format);
        Instant instant=Instant.ofEpochMilli(timestamp);
        ZoneId zoneId=ZoneId.systemDefault();
        LocalDateTime localDateTime=LocalDateTime.ofInstant(instant,zoneId);
        return localDateTime.format(formatter);
    }
}
