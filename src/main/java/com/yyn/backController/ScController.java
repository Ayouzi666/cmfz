package com.yyn.backController;
/*
   
   @author yyn
   @version 1.8
   @create 2019-10-28-19:11
*/


import com.yyn.util.SecurityCode;
import com.yyn.util.SecurityImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("sc")
public class ScController {
    @RequestMapping("sc")
    @ResponseBody
    public void sc(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String securityCode = SecurityCode.getSecurityCode();
        BufferedImage image = SecurityImage.createImage(securityCode);
        HttpSession session = request.getSession();
        session.setAttribute("sc", securityCode);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "png", outputStream);
    }
}
