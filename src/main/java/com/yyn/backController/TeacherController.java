package com.yyn.backController;
/*
   
   @author yyn
   @version 1.8
   @create 2019-11-29-17:33
*/

import com.yyn.entity.Teacher;
import com.yyn.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    private TeacherService TeacherService;

    @RequestMapping("getAll")
    @ResponseBody
    public Map<String, Object> getAll(Integer rows, Integer page) {
        Map<String, Object> req = new HashMap<>();
        List<Teacher> all = TeacherService.getAll((page - 1) * rows, rows);
        Integer tolCount = TeacherService.getCount();
        Integer tolPage = TeacherService.getPage(rows);
        req.put("page", page);
        req.put("total", tolPage);
        req.put("records", tolCount);
        req.put("rows", all);
        return req;
    }

    @ResponseBody
    @RequestMapping("edit")
    public Map edit(String id,String name,String nickname,String picture,String oper){
        Map hashMap = new HashMap();
        Teacher Teacher = new Teacher(null,name,nickname,picture);
        Teacher Teacher1 = new Teacher(id,name,nickname,picture);
        if ("add".equals(oper)) {
            String uuid = UUID.randomUUID().toString();
            Teacher.setId(uuid);
            TeacherService.add(Teacher);
            hashMap.put("pictureid",uuid);
            hashMap.put("status",200);
            return hashMap;
        } else if ("edit".equals(oper)) {
            if (!picture.equals("")) {
                TeacherService.update(Teacher1);
                hashMap.put("pictureid", id);
                hashMap.put("status", 200);
                return hashMap;
            }else {
                Teacher1.setPicture(null);
                TeacherService.update(Teacher1);
                hashMap.put("pictureid", id);
                hashMap.put("status", 200);
                return hashMap;
            }
        } else if ("del".equals(oper)) {
            TeacherService.delete(id);
            return null;
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("upload")
    public void upload(String id, MultipartFile picture, HttpServletRequest request) throws IOException {
        //需要在submit之后进行一次图片路径的修改
        Teacher banner = new Teacher();
        String realPath = request.getSession().getServletContext().getRealPath("img");
        System.out.println(realPath);
        System.out.println(picture.getOriginalFilename().equals(""));
        if (!picture.getOriginalFilename().equals("")) {
            picture.transferTo(new File(realPath, picture.getOriginalFilename()));
            banner.setId(id);
            banner.setPicture(picture.getOriginalFilename());
            TeacherService.update(banner);
        }
    }
}
