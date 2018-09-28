package com.heo.service.impl;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Auth justinniu
 * @Date 2018/9/17
 * @Desc
 */
public class test {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse("2018-09-15 23:23:23");
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.setTime(new Date());
        int y1 = calendar.get(Calendar.YEAR);
        int d1 = calendar.get(Calendar.DAY_OF_YEAR);
        calendar2.setTime(date);

        int y2 = calendar2.get(Calendar.YEAR);
        int d2 = calendar2.get(Calendar.DAY_OF_YEAR);

        if (y1 == y2 && d1 == d2) {
            System.out.println(true);
        }
        else {
            System.out.println(true);
        }
    }
}
