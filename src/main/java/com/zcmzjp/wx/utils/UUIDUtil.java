package com.zcmzjp.wx.utils;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-02-06 10:15
 * Description:
 */
public class UUIDUtil {
    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        return String.valueOf(uuid);
    }

    public static void main(String[] args){
        System.out.println(getUUID());
    }
}
