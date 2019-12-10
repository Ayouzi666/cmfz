package com.yyn;
/*
   
   @author yyn
   @version 1.8
   @create 2019-11-26-11:49
*/

import com.Application;
import com.alibaba.excel.EasyExcel;
import com.yyn.dao.AdminDao;
import com.yyn.dao.PictureDao;
import com.yyn.entity.Picture;
import com.yyn.entity.*;
import com.yyn.service.AdminService;
import com.yyn.service.ArticleService;
import com.yyn.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class test {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private PictureDao pictureDao;
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void test1(){
        /*Admin yyn = adminService.login(new Admin(null, "yyn", "123"));
        System.out.println(yyn);*/
        List<User> all = userService.getAll(0, 3);
        System.out.println(all);
    }

    @Test
    public void test2(){
        /*List<Admin> admins = adminDao.selectByRowBounds(new Admin(), new RowBounds(0, 4));
        System.out.println(admins);*/
//        adminDao.delete(new Admin("5",null,null));
        Admin yyn = adminDao.queryAdminInfo("yyn");
        System.out.println(yyn);
    }

    @Test
    public void test3(){
        List<Picture> pictures = pictureDao.selectAll();
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("轮播图表");
        Row row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("轮播图表信息");
        sheet.setColumnWidth(3,20*256);
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontName("宋体");
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        Row row = sheet.createRow(1);
        row.setHeight((short)900);
        String[] title={"ID","图片名","地址","简介","链接","状态"};
        for (int i=0;i<title.length;i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(cellStyle);
        }
        for (int i=0;i<pictures.size();i++){
            Row row1 = sheet.createRow(i+2);
            Cell cell = row1.createCell(0);
            cell.setCellValue(pictures.get(i).getId());
            row1.createCell(1).setCellValue(pictures.get(i).getName());
            row1.createCell(2).setCellValue(pictures.get(i).getAddress());
            row1.createCell(3).setCellValue(pictures.get(i).getJianjie());
            row1.createCell(4).setCellValue(pictures.get(i).getLink());
            row1.createCell(5).setCellValue(pictures.get(i).getStatus());
        }
        try {
            workbook.write(new FileOutputStream(new File("E:/Test.xls")));
            workbook.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test4(){
        List<DemoData> data =new ArrayList<>();
        DemoData demoData = new DemoData();
        demoData.setString("好看");
        demoData.setDate(new Date());
        demoData.setDoubleData(2.0);
        data.add(demoData);
        data.add(demoData);
        data.add(demoData);
        String fileName = "E:/DemoData.xlsx";
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data);
    }

    @Test
    public void test5(){
        /*GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-e867852a68b24a99822912f9943c4080");
        goEasy.publish("cmfz", "Hello, GoEasy!");*/
        List<Article> a = articleService.getA("a57bb77e-b7bc-4b53-b605-3a12bd500333");
        System.out.println(a);
    }

    @Test
    public void test6(){
        int random = (int) Math.round(Math.random() * 10000);
        /*MessageUtil.sendMessage("13525211904", String.valueOf(random));*/
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("code",String.valueOf(random),60, TimeUnit.SECONDS);
    }

    @Test
    public void test7(){
        int random = (int) Math.round(Math.random() * 10000);
        /*MessageUtil.sendMessage("13525211904", String.valueOf(random));*/
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("code",String.valueOf(random),60, TimeUnit.SECONDS);
    }
}
