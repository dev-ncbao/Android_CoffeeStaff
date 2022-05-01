package com.example.coffeestaff.Commons.Helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {
    public static String convert(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss a");
        return sdf.format(date);
    }

    public static String convertToDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    public static String convertToDate(String date){
        String[] converter = date.split(" - ");
        return converter[0];
    }

    public static boolean compareDate(String date1, String date2){
        if(date1.equals(date2)) return true;
        return false;
    }
}
