package com.yyn.fontController;
/*
   
   @author yyn
   @version 1.8
   @create 2019-12-04-10:11
*/

import com.yyn.dao.UserDao;
import com.yyn.dao.UtDao;
import com.yyn.entity.Teacher;
import com.yyn.entity.User;
import com.yyn.entity.Ut;
import com.yyn.service.UtService;
import com.yyn.util.MD5Utils;
import com.yyn.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("uuser")
public class UuserController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UtDao utDao;
    @Autowired
    private UtService utService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @RequestMapping("login")
    public Map login(String phone, String password, HttpServletRequest request){
        Map<String,Object> map = new  HashMap();
        User user = new User();
        user.setPhone(phone);
        User user2 = userDao.selectOne(user);
        if (user2!=null) {
            String salt = user2.getSalt();
            String password1 = MD5Utils.getPassword(password);
            user.setPassword(password1+salt);
            User user1 = userDao.selectOne(user);
            if (user1 != null) {
                map.put("status", "200");
                map.put("massage", "wdnmd");
                map.put("user", user1);
                request.getSession().setAttribute("user",user1);
            } else {
                map.put("status", "-200");
                map.put("massage", "error");
            }
        }else {
            map.put("status", "-200");
            map.put("massage", "error");
        }
        return map;
    }

    @RequestMapping("regist")
    public Map regist(String id,String phone,String password,String photo,String name,String nickname,String sex,String sign,String location){
        Map<String,Object> map = new HashMap<>();
        User user = new User();
        String salt = MD5Utils.getSalt();
        String password1 = MD5Utils.getPassword(password);
        user.setId(id);
        user.setPhone(phone);
        user.setPassword(password1+salt);
        user.setSalt(salt);
        user.setHead(photo);
        user.setUsername(name);
        user.setNickname(nickname);
        user.setSex(sex);
        user.setSign(sign);
        user.setAddress(location);
        user.setDate(new Date());
        user.setStatus("正常");
        int i = userDao.updateByPrimaryKeySelective(user);
        if (i==1){
            map.put("status","200");
            map.put("user",user);
        }else {
            map.put("status","200");
            map.put("massage","awsl");
        }
        return map;
    }

    @RequestMapping("update")
    public Map update(String id,String nickname,String sex,String photo,String location,String sign,String password){
        Map<String,Object> map = new HashMap<>();
        User user = new User();
        String password1 = MD5Utils.getPassword(password);
        String salt = MD5Utils.getSalt();
        user.setId(id);
        user.setPassword(password1+salt);
        user.setNickname(nickname);
        user.setSex(sex);
        user.setHead(photo);
        user.setAddress(location);
        user.setSign(sign);
        userDao.updateByPrimaryKeySelective(user);
        User user1 = userDao.selectByPrimaryKey(id);
        map.put("status","200");
        map.put("user",user1);
        return map;
    }

    @RequestMapping("guanzhu")
    public Map guanzhu(String uid,String id){
        Map map = new HashMap();
        Ut ut = new Ut();
        ut.setUid(uid);
        ut.setTid(id);
        utDao.insertSelective(ut);
        map.put("status","200");
        List<Teacher> all = utService.getAll(uid);
        map.put("list",all);
        return map;
    }

    @RequestMapping("send")
    public Map send(String phone){
        Map map = new HashMap();
        int code = (int) Math.round(Math.random() * 10000);
        MessageUtil.sendMessage(phone, String.valueOf(code));
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("code",String.valueOf(code),60, TimeUnit.SECONDS);
        map.put("status","200");
        map.put("massage","awsl");
        return map;

    }

    @RequestMapping("input")
    public Map input(String code,String phone){
        Map map = new HashMap();
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String code1 = ops.get("code");
        if (code1.equals(code)){
            User user = new User();
            user.setPhone(phone);
            userDao.insertSelective(user);
            User user1 = userDao.selectOne(user);
            map.put("id",user1.getId());
            map.put("status","200");
            map.put("massage","awsl");
        }else {
            map.put("status","-200");
            map.put("massage","wdnmd");
        }
        return map;
    }
}
