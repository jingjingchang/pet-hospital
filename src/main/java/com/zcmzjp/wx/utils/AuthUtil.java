package com.zcmzjp.wx.utils;

import com.zcmzjp.wx.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-05-16 22:21
 * Description:
 */
public class AuthUtil {
    public static User getCurrentUser(HttpServletRequest request) throws Exception {
        if(request == null){
            return null;
        }

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(user == null) {
             throw new Exception("session为空！");
        }
        return user;
    }
}
