package com.zcmzjp.wx.controller;


import com.zcmzjp.wx.entity.JsonResult;
import com.zcmzjp.wx.entity.Message;
import com.zcmzjp.wx.entity.User;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.UserService;
import com.zcmzjp.wx.utils.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping({"/admin/sys/user"})
public class UserController
        extends BaseController<User>
{
    @Autowired
    UserService userService;

    @Override
    public BaseService<User> getService()
    {
        return this.userService;
    }

    @Override
    public String getViewPrefix()
    {
        return "user";
    }

    @ResponseBody
    @RequestMapping({"/addUser"})
    public Message addUser(User user, String password)
    {
        String pwd = SHA256Util.getSHA256(SHA256Util.getSHA256MixPassword(password));
        user.setPassword(pwd);
        user.setRoleId(1);
        this.userService.add(user);
        return getMessage(true);
    }

    @ResponseBody
    @RequestMapping({"/saveUserRole"})
    public Message saveUserRole(Integer uId, Integer rId)
    {
        User user = userService.getById(uId);
        user.setRoleId(rId.intValue());
        boolean ret = userService.update(user);
        return getMessage(ret);
    }

    @RequestMapping({"/editUserInfo"})
    public String editUserInfo(Model view, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        view.addAttribute("user", user);

        return "editUserInfo";
    }

    @ResponseBody
    @RequestMapping({"/updateUserPwd"})
    public Message updateUserPwd(Model view, Integer id, String pwd, HttpServletRequest request)
    {
        User user = userService.getById(id);
        user.setPassword(SHA256Util.getSHA256(SHA256Util.getSHA256MixPassword(pwd)));
        boolean ret = userService.update(user);
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return getMessage(ret);
    }

    @ResponseBody
    @RequestMapping("/getCurrentUserInfo")
    public JsonResult getCurrentUserInfo(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user!=null){
           User u = userService.getCurrentUserInfo(user.getId());
            return new JsonResult(true,u);
        }else{
            return new JsonResult(false,"非法的请求！");
        }
    }
}
