package com.zhl.java8.time;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @program codeX
 * @description:介绍Java8新增日期API：LocalDate、LocalTime、LocalDateTime
 * @author: meilong
 * @create: 2019/09/07 10:54
 */
public class DateTime {
    /**
     * note:
     * Java8 新增的LocalDate、LocalTime、LocalDateTime 相比于老的Date API具有两个决定性的优势
     * 1）提供的API更加的清晰，方便开发者使用
     * 2）这三个日期类，都是密封类(final修饰)，不允许继承
     * 在我看来，第二点更加的重要，平时在使用Date API的时候，在方法的入参与出参的时候要进行复制操作，比较麻烦，因为要避免：
     * May expose internal representation by returning reference to mutable object
     * 现在LocalDate、LocalTime、LocalDateTime都是不可变的类，就方便多了。
     */
    public static void main(String[] args) {
        // LocalDate
        LocalDate localDate = LocalDate.now();
        // 2019-09-07
        System.out.println(localDate);

        LocalDate designDate = LocalDate.parse("2019-09-08");
        // 2019-09-08
        System.out.println(designDate);

        // LocalTime
        LocalTime localTime = LocalTime.now();
        // 10:57:36.105
        System.out.println(localTime);

        // LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.now();
        // 2019-09-07T10:57:36.105
        System.out.println(localDateTime);

        // DateTimeFormatter
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String now = LocalDateTime.now().format(format);
        // 2019-09-07 11:02:04
        System.out.println(now);

        // Get Time Long 格林尼治时间+8
        Long time = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
        Long time8 = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).getEpochSecond();
        System.out.println("The Get Time Long " + time);
        System.out.println(time8);
    }
}