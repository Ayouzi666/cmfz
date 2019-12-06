package com.yyn.backController;
/*
   
   @author yyn
   @version 1.8
   @create 2019-11-29-11:49
*/


import com.yyn.util.HttpUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("edit")
@RestController
public class EditController {

    @RequestMapping("uploadImg")
    public Map uploadImg(MultipartFile imgFile, HttpSession session, HttpServletRequest request) throws IOException {
        HashMap map = new HashMap();
        try {
            String dir = "/upload/img/";
            String httpUrl = HttpUtil.getHttpUrl(imgFile, request, session, dir);
            map.put("error",0);
            map.put("url",httpUrl);
        }catch (Exception e){
            map.put("error",1);
            map.put("message","上传错误");
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("getImg")
    public Map getImp(HttpSession session,HttpServletRequest request){
        String realPath = session.getServletContext().getRealPath("/upload/img/");
        HashMap hashMap =  new HashMap();
        ArrayList list = new ArrayList();
        File file = new File(realPath);
        File[] files = file.listFiles();
        for (File f : files) {
            HashMap map = new HashMap();
            map.put("is_dir",false);
            map.put("has_file",false);
            map.put("filesize",f.length());
            map.put("is_photo",true);
            String extension = FilenameUtils.getExtension(f.getName());
            map.put("filetype",extension);
            map.put("filename",f.getName());
            String s = f.getName().split("_")[0];
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format = simpleDateFormat.format(new Date(Long.valueOf(s)));
            map.put("datetime",format);
            list.add(map);
        }
        hashMap.put("file_list",list);
        hashMap.put("total_count",list.size());
        hashMap.put("current_url",request.getContextPath()+"/upload/img/");
        return hashMap;
    }
}
