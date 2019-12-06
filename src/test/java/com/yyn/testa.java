package com.yyn;
/*
   
   @author yyn
   @version 1.8
   @create 2019-11-28-9:41
*/


import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import org.junit.Test;

import java.io.File;

public class testa {
    @Test
    public void test() throws EncoderException {
        File file = new File("D:\\迅雷下载\\cmfz\\src\\main\\webapp\\music\\shiting.mp3");
        Encoder encoder = new Encoder();
        long l = 0;
        MultimediaInfo m;
        m = encoder.getInfo(file);
        l = m.getDuration() / 1000;
        System.out.println(l);
    }

    @Test
    public void test1() {

    }
}
