package com.yyn.backController;
/*
   
   @author yyn
   @version 1.8
   @create 2019-11-26-16:33
*/

import com.yyn.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;


}
