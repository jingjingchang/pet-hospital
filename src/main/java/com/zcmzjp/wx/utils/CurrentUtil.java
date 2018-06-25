package com.zcmzjp.wx.utils;

import com.zcmzjp.wx.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-02-05 17:44
 * Description:
 */
public class CurrentUtil {
    public static User getCurrentUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user==null){
            return null;
        }else{
            return user;
        }
    }
}
