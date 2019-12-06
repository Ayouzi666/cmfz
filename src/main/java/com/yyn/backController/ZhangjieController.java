package com.yyn.backController;
/*
   
   @author yyn
   @version 1.8
   @create 2019-11-27-18:41
*/


import com.yyn.entity.Zhangjie;
import com.yyn.service.ZhangjieService;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
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
@RequestMapping("zhangjie")
public class ZhangjieController {
    @Autowired
    private ZhangjieService zhangjieService;

    @RequestMapping("getAll")
    @ResponseBody
    public Map getAll(Integer rows, Integer page,String belong){
        Map<String,Object> req = new HashMap<>();
        List<Zhangjie> all = zhangjieService.getAll((page - 1) * rows, rows, belong);
        Integer total = zhangjieService.getTotal(belong);
        Integer tolPage = zhangjieService.getTolPage(belong, rows);
        req.put("page", page);
        req.put("total", tolPage);
        req.put("records", total);
        req.put("rows", all);
        return req;
    }

    @RequestMapping("edit")
    @ResponseBody
    public Map edit(String id,String name,String size,String time,String path,String belong,String oper) {
        HashMap hashMap = new HashMap();
        Zhangjie zhangjie = new Zhangjie(null,name,size,time,path,belong);
        Zhangjie zhangjie1 = new Zhangjie(id,name,size,time,path,belong);
        if ("add".equals(oper)) {
            String uuid = UUID.randomUUID().toString();
            zhangjie.setId(uuid);
            zhangjieService.add(zhangjie);
            hashMap.put("pictureid",uuid);
            hashMap.put("status",200);
            return hashMap;
        } else if ("edit".equals(oper)) {
            if (!path.equals("")) {
                zhangjieService.update(zhangjie1);
                hashMap.put("pictureid", id);
                hashMap.put("status", 200);
                return hashMap;
            }else {
                zhangjie1.setPath(null);
                zhangjieService.update(zhangjie1);
                hashMap.put("pictureid", id);
                hashMap.put("status", 200);
                return hashMap;
            }
        } else if ("del".equals(oper)) {
            zhangjieService.delete(id);
            return null;
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("upload")
    public void upload(String id, MultipartFile path, HttpServletRequest request) throws IOException, EncoderException {
        //需要在submit之后进行一次图片路径的修改
        MultimediaInfo m;
        long l = 0;
        double size = 0;
        Zhangjie banner = new Zhangjie();
        System.out.println(id);
        String realPath = request.getSession().getServletContext().getRealPath("music");
        System.out.println(realPath);
        System.out.println(path.getOriginalFilename().equals(""));
        if (!path.getOriginalFilename().equals("")) {
            path.transferTo(new File(realPath, path.getOriginalFilename()));
            Encoder encoder = new Encoder();
            m = encoder.getInfo(new File(realPath, path.getOriginalFilename()));
            l = m.getDuration()/1000;
            size = new File(realPath, path.getOriginalFilename()).length()/1024.0/1024;
            size = (double) Math.round(size * 100) / 100;
            banner.setId(id);
            banner.setPath(path.getOriginalFilename());
            banner.setSize(String.valueOf(size)+"MB");
            banner.setTime(String.valueOf(String.valueOf(l/60)+":"+String.valueOf(l%60)));
            zhangjieService.update(banner);
        }
    }
}
