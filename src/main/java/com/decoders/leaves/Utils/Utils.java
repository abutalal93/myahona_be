package com.decoders.leaves.Utils;

import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;

public class Utils {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        Utils.applicationContext = applicationContext;
    }

    public static String generateLeaveNumber() {
        LocalDateTime localDateTime = LocalDateTime.now();

        String leaveNumber= localDateTime.getYear()+""+localDateTime.getMonthValue()+""+localDateTime.getDayOfMonth()+""+localDateTime.getHour()+""+localDateTime.getMinute()+""+localDateTime.getSecond();
        System.out.println("leaveNumber: "+leaveNumber);
        System.out.println("leaveNumber: "+leaveNumber.length());
        return leaveNumber;
    }
}