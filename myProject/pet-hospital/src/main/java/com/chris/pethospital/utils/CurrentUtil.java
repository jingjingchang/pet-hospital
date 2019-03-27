package com.chris.pethospital.utils;


import com.chris.pethospital.entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-02-05 17:44
 * Description:
 */
public class CurrentUtil {
    public static User getCurrentUser(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user==null){
            return null;
        }else{
            return user;
        }
    }
}
