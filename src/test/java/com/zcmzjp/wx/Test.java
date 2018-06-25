package com.zcmzjp.wx;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-04-17 10:25
 * Description:
 */
public class Test {


    public static void main(String[] orgs) throws Exception {
        String str ="15:16";
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        if (sdf.format(date).equals(sdf.format(sdf.parse(str)))) {
            System.out.println(11111111);
        }
    }

}
