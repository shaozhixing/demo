package com.example.demo.controller;

import com.example.demo.model.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView A(@RequestParam(value = "str") String str){
        A a = controller1.hi();
        System.out.println("result----》" + a);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("test");
        mav.addObject("A",a);
        return mav;
    }


}
