package com.yyn.backController;
/*
   
   @author yyn
   @version 1.8
   @create 2019-11-27-16:47
*/

import com.yyn.entity.Zhuanji;
import com.yyn.service.ZhuanjiService;
import com.yyn.util.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("zhuanji")
public class Zhuanjicontroller {
    @Autowired
    private ZhuanjiService zhuanjiService;

    @RequestMapping("getAll")
    @ResponseBody
    public Map getAll(Integer rows, Integer page){
        Map<String,Object> req = new HashMap<>();
        List<Zhuanji> all = zhuanjiService.getAll((page - 1) * rows, rows);
        Integer tolCount = zhuanjiService.getTotal();
        Integer tolPage = zhuanjiService.getTolPage(rows);
        req.put("page", page);
        req.put("total", tolPage);
        req.put("records", tolCount);
        req.put("rows", all);
        return req;
    }


    @RequestMapping("edit")
    @ResponseBody
    public Map edit(String id, String tatle, String picture, String author, String boyin, String jishu, String jianjie, String date,String start, String oper) {
        HashMap hashMap = new HashMap();
        Date date1 = utils.conversStrDate(date);
        Zhuanji zhuanji = new Zhuanji(null,tatle,picture,author,boyin,jishu,jianjie,date1,start);
        Zhuanji zhuanji1 = new Zhuanji(id,tatle,picture,author,boyin,jishu,jianjie,date1,start);
        if ("add".equals(oper)) {
            String uuid = UUID.randomUUID().toString();
            zhuanji.setId(uuid);
            zhuanjiService.add(zhuanji);
            hashMap.put("pictureid",uuid);
            hashMap.put("status",200);
            return hashMap;
        } else if ("edit".equals(oper)) {
            if (!picture.equals("")) {
                zhuanjiService.update(zhuanji1);
                hashMap.put("pictureid", id);
                hashMap.put("status", 200);
                return hashMap;
            }else {
                zhuanji1.setPicture(null);
                zhuanjiService.update(zhuanji1);
                hashMap.put("pictureid", id);
                hashMap.put("status", 200);
                return hashMap;
            }
        } else if ("del".equals(oper)) {
            zhuanjiService.delete(id);
            return null;
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("upload")
    public void upload(String id, MultipartFile picture, HttpServletRequest request) throws IOException {
        //需要在submit之后进行一次图片路径的修改
        Zhuanji banner = new Zhuanji();
        System.out.println(id);
        String realPath = request.getSession().getServletContext().getRealPath("img");
        System.out.println(realPath);
        System.out.println(picture.getOriginalFilename().equals(""));
        if (!picture.getOriginalFilename().equals("")) {
            picture.transferTo(new File(realPath, picture.getOriginalFilename()));
            banner.setId(id);
            banner.setPicture(picture.getOriginalFilename());
            zhuanjiService.update(banner);
        }
    }

    @RequestMapping("All")
    public void All(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        List<Zhuanji> all = zhuanjiService.All();
        PrintWriter writer = response.getWriter();
        writer.println("<select>");
        for (Zhuanji d : all) {
            writer.println("<option value='" + d.getId() + "'>");
            writer.println(d.getTatle());
            writer.println("</option>");
        }
        writer.println("</select>");
        writer.close();
    }
}
