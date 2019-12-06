package com.yyn.backController;
/*
   
   @author yyn
   @version 1.8
   @create 2019-11-26-18:59
*/

import com.yyn.entity.Picture;
import com.yyn.service.PictureService;
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
@RequestMapping("picture")
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @RequestMapping("getAll")
    @ResponseBody
    public Map<String, Object> getAll(Integer rows, Integer page, String searchField, String searchString, String searchOper, HttpServletRequest request) {
        Map<String, Object> req = new HashMap<>();
        List<Picture> all = pictureService.getAll((page - 1) * rows, rows);
        Integer tolCount = pictureService.getTotal();
        Integer tolPage = pictureService.getTolPage(rows);
        req.put("page", page);
        req.put("total", tolPage);
        req.put("records", tolCount);
        req.put("rows", all);
        return req;
    }

    @ResponseBody
    @RequestMapping("edit")
    public Map edit(String id, String name, String address, String jianjie, String link, String status, String oper) {
        HashMap hashMap = new HashMap();
        Picture picture = new Picture(null, name, address, jianjie, link, status);
        Picture picture1 = new Picture(id, name, address, jianjie, link, status);
        if ("add".equals(oper)) {
            String uuid = UUID.randomUUID().toString();
            picture.setId(uuid);
            pictureService.add(picture);
            hashMap.put("pictureid",uuid);
            hashMap.put("status",200);
            return hashMap;
        } else if ("edit".equals(oper)) {
            if (!address.equals("")) {
                pictureService.update(picture1);
                hashMap.put("pictureid", id);
                hashMap.put("status", 200);
                return hashMap;
            }else {
                picture1.setAddress(null);
                pictureService.update(picture1);
                hashMap.put("pictureid", id);
                hashMap.put("status", 200);
                return hashMap;
            }
        } else if ("del".equals(oper)) {
            pictureService.delete(id);
            return null;
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("upload")
    public void upload(String id, MultipartFile address,HttpServletRequest request) throws IOException {
        //需要在submit之后进行一次图片路径的修改
        Picture banner = new Picture();
        String realPath = request.getSession().getServletContext().getRealPath("img");
        System.out.println(realPath);
        System.out.println(address.getOriginalFilename().equals(""));
        if (!address.getOriginalFilename().equals("")) {
            address.transferTo(new File(realPath, address.getOriginalFilename()));
            banner.setId(id);
            banner.setAddress(address.getOriginalFilename());
            pictureService.update(banner);
        }
    }
}
