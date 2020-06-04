package com.esgi.microservices.services;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String today(){
        return  new SimpleDateFormat().format(new Date());
    }
}
