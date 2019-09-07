package com.example.demo.contants;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author: shaozhixing
 * @date: 2019/8/30 11:43
 * @copyright: gofun
 */
public class AdminUserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        System.out.println("请求路径=" + request.getRequestURI());
        Map map = request.getParameterMap();
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                 Exception ex) throws Exception {
        System.out.println("方法完成---》");
    }
}
