package com.software.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserLogDaoTest {
    @Test
    public void TestFunction1() {
        // 格式化日期时间为 "yyyy-MM-dd HH:mm:ss" 格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 获取当前日期和时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println("当前日期和时间: " + now.format(formatter));
        // 获取三个月后的日期和时间
        LocalDateTime threeMonthsLater = now.plusMonths(3);
        System.out.println("三个月后的日期和时间: " + threeMonthsLater.format(formatter));
    }
}
