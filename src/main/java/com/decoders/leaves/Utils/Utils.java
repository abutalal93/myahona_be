package com.decoders.leaves.Utils;

import org.springframework.context.ApplicationContext;

public class Utils {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        Utils.applicationContext = applicationContext;
    }
}