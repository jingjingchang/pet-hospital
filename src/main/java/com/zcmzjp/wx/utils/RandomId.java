package com.zcmzjp.wx.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by Chris on 2017-07-18.
 */
public class RandomId {

    private Random random;
    private String table;
    public RandomId() {
        random = new Random();
        table = "0123456789";
    }
    public String randomId(int id) {
        String ret = null,
                num = String.format("%05d", id);
        int key = random.nextInt(10),
            seed = random.nextInt(100);
        Caesar caesar = new Caesar(table, seed);
        num = caesar.encode(key, num);
        ret = num+ String.format("%01d", key) + String.format("%02d", seed);

        return ret;
    }
    public static void main(String[] args) {
        RandomId r = new RandomId();
        String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" };
        String time = "2017-07-28";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date tmpDate = null;
        try {
            tmpDate = format.parse(time);
            calendar.setTime(tmpDate);
            int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            System.out.println(weekDaysName[intWeek]);
        } catch (ParseException e) {
            e.printStackTrace();
        }


       /* for (int i = 0; i < 30; i += 1) {
            System.out.println(r.randomId(i));
        }*/
    }
}
