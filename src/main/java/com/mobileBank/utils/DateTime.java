package com.mobileBank.utils;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateTime {
    private String date;
    private Date datetime;

    public DateTime() {
    }

    public DateTime(String date) {
        this.date = date;
    }

    public DateTime(Date datetime) {
        this.datetime = datetime;
    }

    public DateTime(String date, Date datetime) {
        this.date = date;
        this.datetime = datetime;
    }

    public static String getNowDateString() {
        Date date = new Date();
        // 格式化日期时间为字符串
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date);
    }

    @SneakyThrows
    public static Date getNowDate() {
        // 定义日期格式为"yyyy-MM-dd"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 获取当前日期，将其格式化为字符串后转换为Date输出（这里默认时间为00:00:00）
        return sdf.parse(sdf.format(new Date()));
    }

    public static Date getNowDateTime() {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        // 将LocalDateTime转换为Date输出
        return Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date getFirstDate() {
        // 创建一个Calendar实例，并设置时间为今天
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        // 将Calendar设置为本月的第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        // 本月第一天
        return calendar.getTime();
    }

    public static Date getAfterDateTime() {
        // 获取三个月后的时间
        LocalDateTime afterDateTime = LocalDateTime.now().plusMonths(3);
        // 将LocalDateTime转换为Date输出
        return Date.from(afterDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
