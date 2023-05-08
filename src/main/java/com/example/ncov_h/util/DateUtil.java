package com.example.ncov_h.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    
    public static String getCurrentDatePath()throws Exception{
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddmmssSSSSSSSSS");
        return simpleDateFormat.format(date);
    }
    
}
