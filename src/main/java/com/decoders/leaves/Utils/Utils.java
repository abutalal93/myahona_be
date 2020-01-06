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

    public static String prepareEmailJsonMessage(String fromEmail , String fromName , String toEmail , String toName , String subject , String htmlPart){

        String messageJson = "{\n" +
                "    \"Messages\": [\n" +
                "        {\n" +
                "            \"From\": {\n" +
                "                \"Email\": \""+fromEmail+"\",\n" +
                "                \"Name\": \""+fromName+"\"\n" +
                "            },\n" +
                "            \"To\": [\n" +
                "                {\n" +
                "                    \"Email\": \""+toEmail+"\",\n" +
                "                    \"Name\": \""+toName+"\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"Subject\": \""+subject+"\",\n" +
                "            \"TextPart\": \"\",\n" +
                "            \"HTMLPart\": \""+htmlPart+"\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        return messageJson;
    }
}