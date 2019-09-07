package com.example.demo.controller;

import java.lang.annotation.*;

/**
 * @author: shaozhixing
 * @date: 2019/7/8 13:41
 * @copyright: gofun
 */
@Documented
@Target({ ElementType.METHOD,ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Hello {
}
