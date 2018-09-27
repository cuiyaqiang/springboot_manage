package com.guye.sun;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by suneee on 2018/9/25.
 */
public class DateTest {

    @Test
    public void test1(){
        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = LocalDate.MAX;
        LocalDate localDate2 = LocalDate.MIN;
        LocalDate localDate3 = LocalDate.parse("2011-01-21");
        LocalDate localDate4 = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println(localDate);
        System.out.println(localDate1);
        System.out.println(localDate2);
        System.out.println(localDate3);
        System.out.println(localDate4);

        LocalTime localTime = LocalTime.now();
        LocalTime localTime1 = LocalTime.MAX;
        LocalTime localTime2 = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        System.out.println(localTime);
        System.out.println(localTime1);
        System.out.println(localTime2);

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println(localDateTime);
        System.out.println(localDateTime1);
    }


}
