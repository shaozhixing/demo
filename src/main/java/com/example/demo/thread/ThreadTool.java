package com.example.demo.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @author: shaozhixing
 * @date: 2019/7/18 18:08
 * @copyright: gofun
 */
@Component
public class ThreadTool {

    private static ExecutorService singleThreadPool = null;

    private ThreadTool(){
    }
    public static ExecutorService getIntanst(){
        if (singleThreadPool == null) {
            synchronized (ThreadTool.class){
                if (singleThreadPool == null) {
                    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                            .setNameFormat("demo-pool-%d").build();
                    singleThreadPool = new ThreadPoolExecutor(1, 100,
                            0L, TimeUnit.MILLISECONDS,
                            new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
                    System.out.println("name---->" + singleThreadPool.getClass().getName());
                }
            }
        }
        return singleThreadPool;
    }
}
