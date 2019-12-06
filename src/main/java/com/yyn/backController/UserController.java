package com.yyn.backController;
/*
   
   @author yyn
   @version 1.8
   @create 2019-11-29-17:33
*/

import com.google.gson.Gson;
import com.yyn.entity.MapVO;
import com.yyn.entity.User;
import com.yyn.service.UserService;
import com.yyn.util.MD5Utils;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("getAll")
    @ResponseBody
    public Map<String, Object> getAll(Integer rows, Integer page) {
        Map<String, Object> req = new HashMap<>();
        List<User> all = userService.getAll((page - 1) * rows, rows);
        Integer tolCount = userService.getCount();
        Integer tolPage = userService.getPage(rows);
        req.put("page", page);
        req.put("total", tolPage);
        req.put("records", tolCount);
        req.put("rows", all);
        return req;
    }

    @ResponseBody
    @RequestMapping("edit")
    public Map edit(String id,String phone,String password,String status,String username,String nickname,String sex,String sign,String head,String address,String oper){
        Map hashMap = new HashMap();
        String password1="";
        String salt = MD5Utils.getSalt();
        if (password!=null) {
             password1 = MD5Utils.getPassword(password);
        }
        User user = new User(null,phone,password1+salt,salt,status,username,nickname,sex,sign,head,address,new Date());
        User user1 = new User(id,phone,password1+salt,salt,status,username,nickname,sex,sign,head,address,null);
        if ("add".equals(oper)) {
            String uuid = UUID.randomUUID().toString();
            user.setId(uuid);
            userService.add(user);
            hashMap.put("pictureid",uuid);
            hashMap.put("status",200);
            Map<String, Object> req = new HashMap<>();
            Integer m1 = userService.getUserByTime("男", 1);
            Integer m7 = userService.getUserByTime("男", 7);
            Integer m30 = userService.getUserByTime("男", 30);
            Integer m365 = userService.getUserByTime("男", 365);
            Integer n1 = userService.getUserByTime("女", 1);
            Integer n7 = userService.getUserByTime("女", 7);
            Integer n30 = userService.getUserByTime("女", 30);
            Integer n365 = userService.getUserByTime("女", 365);
            req.put("m1",m1);
            req.put("m7",m7);
            req.put("m30",m30);
            req.put("m365",m365);
            req.put("n1",n1);
            req.put("n7",n7);
            req.put("n30",n30);
            req.put("n365",n365);
            List<MapVO> add = userService.getAddress();
            Map<String,Object> map = new HashMap<>();
            map.put("count",req);
            map.put("add",add);
            Gson gson = new Gson();
            String json = gson.toJson(map);
            GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-e867852a68b24a99822912f9943c4080");
            goEasy.publish("cmfz", json);
            return hashMap;
        } else if ("edit".equals(oper)) {
            if (!head.equals("")) {
                userService.update(user1);
                hashMap.put("pictureid", id);
                hashMap.put("status", 200);
                return hashMap;
            }else {
                user1.setHead(null);
                userService.update(user1);
                hashMap.put("pictureid", id);
                hashMap.put("status", 200);
                return hashMap;
            }
        } else if ("del".equals(oper)) {
            userService.delete(id);
            return null;
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("upload")
    public void upload(String id, MultipartFile head, HttpServletRequest request) throws IOException {
        //需要在submit之后进行一次图片路径的修改
        User banner = new User();
        String realPath = request.getSession().getServletContext().getRealPath("img");
        System.out.println(realPath);
        System.out.println(head.getOriginalFilename().equals(""));
        if (!head.getOriginalFilename().equals("")) {
            head.transferTo(new File(realPath, head.getOriginalFilename()));
            banner.setId(id);
            banner.setHead(head.getOriginalFilename());
            userService.update(banner);
        }
    }

    @ResponseBody
    @RequestMapping("get")
    public Map get(){
        Map<String, Object> req = new HashMap<>();
        Integer m1 = userService.getUserByTime("男", 1);
        Integer m7 = userService.getUserByTime("男", 7);
        Integer m30 = userService.getUserByTime("男", 30);
        Integer m365 = userService.getUserByTime("男", 365);
        Integer n1 = userService.getUserByTime("女", 1);
        Integer n7 = userService.getUserByTime("女", 7);
        Integer n30 = userService.getUserByTime("女", 30);
        Integer n365 = userService.getUserByTime("女", 365);
        req.put("m1",m1);
        req.put("m7",m7);
        req.put("m30",m30);
        req.put("m365",m365);
        req.put("n1",n1);
        req.put("n7",n7);
        req.put("n30",n30);
        req.put("n365",n365);
        return req;
    }

    @ResponseBody
    @RequestMapping("getAddress")
    public List getAddress(){
        List<MapVO> address = userService.getAddress();
        return address;
    }
}
