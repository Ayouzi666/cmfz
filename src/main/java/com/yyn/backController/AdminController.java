package com.yyn.backController;
/*
   
   @author yyn
   @version 1.8
   @create 2019-11-26-16:33
*/

import com.yyn.entity.Admin;
import com.yyn.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("login")
    public String login(String username, String password, String sc, HttpServletRequest request){
        Admin admin = new Admin(null,username,password);
        String sc1 = (String) request.getSession().getAttribute("sc");
        if (sc1.equals(sc)){
            Admin login = adminService.login(admin);
            if (login!=null){
                request.getSession().removeAttribute("error");
                return "redirect:/jsp/themain.jsp";
            }else {
                request.getSession().setAttribute("error","账号或密码输入错误");
                return "redirect:/jsp/login.jsp";
            }
        }else {
            request.getSession().setAttribute("error","验证码输入错误");
            return "redirect:/jsp/login.jsp";
        }
    }
}
