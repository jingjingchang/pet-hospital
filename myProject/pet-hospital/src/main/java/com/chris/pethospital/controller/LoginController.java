package com.chris.pethospital.controller;

import com.chris.pethospital.entity.JsonResult;
import com.chris.pethospital.entity.User;
import com.chris.pethospital.service.UserService;
import com.chris.pethospital.utils.SHA256Util;
import com.chris.pethospital.utils.ValidateCodeUtils;
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


@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("")
    public String login(Model view,HttpServletRequest request, HttpServletResponse response) throws IOException {
        //在session中查找是否有user这个key（session是使用key value形式存储的）
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            //如果没有user登录信息就重定向到login页面
            return "login";
            //如果是普通用户
        }else if (1==user.getType() ) {
            view.addAttribute("user",user);
            return "index";
        } else {
            view.addAttribute("user",user);
            return "admin";
        }

    }

    @RequestMapping("/index")
    public String index(Model view,HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return "login";
        }
        if (user.getType() == 1) {
            view.addAttribute("user",user);
            return "index";
        } else {
            view.addAttribute("user",user);
            // 如果是超级用户则返回index页面
            return "admin";
        }
    }

    @RequestMapping({"/login"})
    public String index(Model view) {
        return "login";
    }


    @RequestMapping({"/register"})
    public String register(Model view) {
        return "register";
    }

    @ResponseBody
    @RequestMapping(value = {"/imagevcode"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET}, produces = {"image/jpeg"})
    public byte[] validateCodeImage(HttpServletRequest request)
            throws IOException {
        BufferedImage image = ValidateCodeUtils.getValidateCodeImage(request.getSession());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        return baos.toByteArray();
    }

    @ResponseBody
    @RequestMapping({"/user/login"})
    public JsonResult login(String username, String password, String verifyCode, HttpServletRequest request) {
        User user = userService.userLogin(username, password);
        if (!ValidateCodeUtils.getValidateCode(request.getSession()).equals(verifyCode)) {
            return new JsonResult(false, "验证码错误",null);
        }
        if (user == null) {
            return new JsonResult(false, "用户名或密码错误",null);
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        return new JsonResult(true, "登录成功",null);
    }

    @RequestMapping({"/user/logout"})
    public String logout(HttpServletRequest request, HttpServletResponse response, Model view) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "login";
    }

    @RequestMapping("/user/register")
    @ResponseBody
    public JsonResult register(User user){
        User user1 = userService.getByUsername(user.getUsername());
        if(user1!=null){
            JsonResult result = new JsonResult();
            result.setSuccess(false);
            result.setMessage("用户名已存在！");
            return result;
        }else{
            String s = SHA256Util.getSHA256(SHA256Util.getSHA256MixPassword(user.getPassword()));
            user.setPassword(s);
            user.setType(1);
            user.setStatus(0);
            JsonResult jsonResult = userService.add(user);
            if(jsonResult.isSuccess()){
                jsonResult.setMessage("注册成功！");
            }
            return jsonResult;
        }
    }


}
