package com.zcmzjp.wx.controller;


import com.zcmzjp.wx.entity.JsonResult;
import com.zcmzjp.wx.entity.Teacher;
import com.zcmzjp.wx.entity.User;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.TeacherService;
import com.zcmzjp.wx.service.UserService;
import com.zcmzjp.wx.utils.SHA256Util;
import com.zcmzjp.wx.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class LoginController extends BaseController<User>{
    @Autowired
    UserService userService;

    @Autowired
    TeacherService teacherService;

    @RequestMapping({"/login"})
    public String index(Model view)
    {
        return "login";
    }

    @ResponseBody
    @RequestMapping(value={"/imagevcode"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"image/jpeg"})
    public byte[] validateCodeImage(HttpServletRequest request)
            throws IOException
    {
        BufferedImage image = ValidateCodeUtils.getValidateCodeImage(request.getSession());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        return baos.toByteArray();
    }

    @ResponseBody
    @RequestMapping({"/user/login"})
    public JsonResult login(String username, String password, String verifyCode, HttpServletRequest request, Boolean rememberMe)
    {
        String host = request.getRemoteHost();

        User user = userService.userLogin(username, password);
        if (!ValidateCodeUtils.getValidateCode(request.getSession()).equals(verifyCode)) {
            return new JsonResult(false, "验证码错误");
        }
        if (user == null) {
            return new JsonResult(false, "用户名或密码错误");
        }
        HttpSession session = request.getSession();

        session.setAttribute("user", user);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setLastLoginDate(sdf.format(date));
        user.setLastLoginIp(host);
        boolean flag = userService.update(user);
        if (flag) {
            System.out.println(flag);
        } else {
            System.out.println(flag);
        }
        return new JsonResult(true, "登录成功");
    }

    @RequestMapping({"/user/logout"})
    public String logout(HttpServletRequest request, HttpServletResponse response, Model view)
    {
        String host = request.getRemoteHost();
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "login";
    }

    @RequestMapping("/teacherLoginPage")
    public String teaherLoginPage(){
        return "teacherLogin";
    }

    @ResponseBody
    @RequestMapping({"/user/teacherLogin"})
    public JsonResult teacherLogin(String username, String password, String verifyCode, HttpServletRequest request, Boolean rememberMe)
    {
        String host = request.getRemoteHost();

        Teacher teacher = teacherService.teacherLogin(username, SHA256Util.getSHA256(SHA256Util.getSHA256MixPassword(password)));
        if (!ValidateCodeUtils.getValidateCode(request.getSession()).equals(verifyCode)) {
            return new JsonResult(false, "验证码错误");
        }
        if (teacher == null) {
            return new JsonResult(false, "用户名或密码错误");
        }
        HttpSession session = request.getSession();
        session.setAttribute("teacher", teacher);
        return new JsonResult(true, "登录成功");
    }

    @RequestMapping({"/user/teacherLogout"})
    public String teacherLogout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("teacher");
        return "teacherLogin";
    }

    @Override
    public BaseService<User> getService()
    {
        return null;
    }

    @Override
    public String getViewPrefix()
    {
        return null;
    }
}
