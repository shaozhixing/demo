package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.cache.LruTTLCache;
import com.example.demo.cache.LruTTLCacheManger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author: shaozhixing
 * @date: 2019/7/15 14:38
 * @copyright: gofun
 */
@Aspect
@Component
public class HelloAspect {

    private static final String POINT_CUT = "@annotation(com.example.demo.controller.Hello)";

    private LruTTLCache lruTTLCache = LruTTLCacheManger.getLruCacheInstance();

    @Pointcut(POINT_CUT)
    public void method(){

    }

    @Around("method()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        String sss = this.getCacheKey(null, targetName, methodName, arguments);
        System.out.println("sssssssssss------>>"+sss);
        /*String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println("HELLO->method--------------" + methodName);
        String key = className + "." + methodName;
        Object result = lruTTLCache.get(key);
        if (result == null) {
            result = joinPoint.proceed();
            lruTTLCache.put(key, result);
        }
        List<String> l = lruTTLCache.searchKeys("com");*/
        return null;
    }

    public String getCacheKey(String key , String targetName, String methodName,
                              Object[] arguments) {
        StringBuffer sb = new StringBuffer();
        if(key != null){
            sb.append(key);
        }else{
            sb.append(targetName).append(".").append(methodName);
        }
        if (arguments != null && (arguments.length != 0)) {
            sb.append("#").append(JSON.toJSONString(arguments));
        }
        return sb.toString().replace("[","").replace("\"","").replace("]","").replace("com.gofun.","");
    }
}
