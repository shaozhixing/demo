package com.example.demo.controller;

import com.example.demo.mapper.AdminMapper;
import com.example.demo.model.A;
import com.example.demo.thread.ThreadTool;
import com.example.demo.vo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ExecutorService;

/**
 * @author: shaozhixing
 * @date: 2019/7/8 12:30
 * @copyright: gofun
 */
@Controller
@RequestMapping("/v1")
public class Controller1 {

    ExecutorService executorService = ThreadTool.getIntanst();

    @Autowired
    private AdminMapper adminMapper;

    @Hello
//    @Scheduled(cron = "*/1 * * * * ?")
    public Admin hi(){
        A a = new A();
        a.setA("a");
        a.setB("b");
        a.setC("c");
        System.out.println("走到方法了");
        /*for (int i = 0 ; i < 100; i++) {
            final int s = i;
            executorService.execute(() -> {
                System.out.print(System.currentTimeMillis() + "-------");
                System.out.println(s);

            });

        }*/
        Admin admin = adminMapper.selectByPrimaryKey("a1b053a3d5114e3f98e4a397de2fb029");
        return admin;
    }

    public static void main(String[] args) {
        Object o = null;
        System.out.println(o.getClass().getName());
    }

}
