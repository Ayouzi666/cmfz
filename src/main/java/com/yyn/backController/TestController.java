package com.yyn.backController;
/*
   
   @author yyn
   @version 1.8
   @create 2019-12-03-15:42
*/

import com.yyn.task.ControllerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("test")
@RestController
public class TestController {
    @Autowired
    private ControllerTask controllerTask;
    @RequestMapping("test")
    public String test(){
        controllerTask.run();
        return "OK";
    }
}
