package com.sfes.controller;

import com.sfes.pojo.Admin;
import com.sfes.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : sfes
 * @date : 2023/10/28
 */
@Controller
@RequestMapping("/admin")
public class AdminAction {

    @Autowired
    private AdminService adminService;
    //实现登录判断并进行相应跳转

    @RequestMapping("/login")
    public String login(String name, String pwd, HttpServletRequest request){
        Admin admin = adminService.login(name, pwd);
        if(admin!=null){
            //登陆成功
            request.setAttribute("admin", admin);
            return "main";
        }else {
            //失败
            request.setAttribute("errmsg", "用户名或密码不正确!");
            return "login";
        }
    }



}
