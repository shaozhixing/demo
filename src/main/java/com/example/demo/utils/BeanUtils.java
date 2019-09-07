package com.example.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public abstract class BeanUtils extends org.springframework.beans.BeanUtils {

    private static final ConcurrentMap<String, HashMap<String, String>> fieldNameCache = new ConcurrentHashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(BeanUtils.class);


    /**
     * 利用序列化深度克隆
     *
     * @param src
     * @return
     * @throws Exception
     */
    public static Object clone(Object src) throws Exception {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        Object newObj = in.readObject();
        return newObj;
    }
}
