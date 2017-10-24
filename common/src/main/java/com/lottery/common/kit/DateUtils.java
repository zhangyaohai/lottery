package com.lottery.common.kit;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {
    public static boolean isDate(String str){
        boolean isDate = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            sdf.parse(str);
            isDate = true;
        } catch (ParseException e) {
            isDate = false;
        }

        return isDate;
    }

    public static void main(String[] args) {
        System.out.println(isDate("2017-08-13T12:36:39+800"));
    }
}
