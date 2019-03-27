package com.chris.pethospital.controller;


import com.chris.pethospital.entity.JsonResult;
import com.chris.pethospital.entity.User;
import com.chris.pethospital.service.BaseService;
import com.chris.pethospital.service.UserService;
import com.chris.pethospital.utils.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User> {

    @Autowired
    UserService userService;

    @Override
    public BaseService<User> getService() {
        return userService;
    }

    @Override
    public String getViewPrefix() {
        return "user";
    }


    @RequestMapping({"/editUser"})
    public String editUser(Model view) {
        return "changePassword";
    }

    @RequestMapping({"/editMyInfo"})
    public String editMyInfo(Model view, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("user");
        view.addAttribute("user",sessionUser);
        return getViewPrefix()+"/editmyinfo";
    }

    @ResponseBody
    @RequestMapping({"/updateMyInfo"})
    public JsonResult updateMyInfo(Model view, User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("user",user);
       return userService.update(user);
    }


    @ResponseBody
    @RequestMapping({"/updateUserPwd"})
    public JsonResult updateUserPwd(Model view, Integer id, String pwd,String oldPwd, HttpServletRequest request) {
        JsonResult result = new JsonResult();
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("user");
        User user = userService.userLogin(sessionUser.getUsername(),oldPwd);
        if(user==null){
            result.setMessage("旧密码验证错误！");
            result.setSuccess(false);
            return  result;
        }else{
            user.setPassword(SHA256Util.getSHA256(SHA256Util.getSHA256MixPassword(pwd)));
            JsonResult ret = userService.update(user);
            session.removeAttribute("user");
            return ret;
        }
    }
}
