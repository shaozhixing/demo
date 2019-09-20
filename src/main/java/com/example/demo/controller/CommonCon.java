package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.vo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: shaozhixing
 * @date: 2019/7/17 13:39
 * @copyright: gofun
 */
@Controller
@RequestMapping("/common")
public class CommonCon {

    @Autowired
    private Controller1 controller1;

    @RequestMapping("/A")
    @ResponseBody
    public Admin A(@RequestParam(value = "str") String str){
        Admin a = controller1.hi();
        System.out.println("result----》" + JSON.toJSONString(a));
        return a;
    }


}
