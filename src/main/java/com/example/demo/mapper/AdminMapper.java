package com.example.demo.mapper;

import com.example.demo.vo.Admin;

/**
 * @author: shaozhixing
 * @date: 2019/8/31 11:16
 * @copyright: gofun
 */
public interface AdminMapper {
    Admin selectByPrimaryKey (String id);
}
