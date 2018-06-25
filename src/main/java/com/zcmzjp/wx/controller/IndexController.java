package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class IndexController {

    @RequestMapping("")
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Object obj = request.getSession().getAttribute("user");
        if (obj == null || !(obj instanceof User)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return "login";
        }
        return "index";
    }
    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Object obj = request.getSession().getAttribute("user");
        if (obj == null || !(obj instanceof User)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return "login";
        }
        return "index";
    }
    @RequestMapping("/openPage/{page}")
    public String page(@PathVariable String page){
        return page;
    }

}
