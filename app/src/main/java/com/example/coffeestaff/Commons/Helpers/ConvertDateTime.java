package com.example.coffeestaff.Commons.Helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertDateTime {
    public static String convert(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss a");
        return sdf.format(date);
    }
}
