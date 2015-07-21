package com.cmcc.edu.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {

    @RequestMapping(value = "/login"    )
    public String showLoginForm(HttpServletRequest req, Model model) {
        String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(ExcessiveAttemptsException.class.getName().equals(exceptionClassName)) {
            error = "用户密码输入错误超过5次，账号已被锁，请联系管理员";
        }else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("error", error);
        return "login";
    }


}
