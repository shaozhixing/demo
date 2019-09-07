package com.example.demo.contants;

import com.example.demo.thread.ThreadTool;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

/**
 * @author: shaozhixing
 * @date: 2019/7/18 18:17
 * @copyright: gofun
 */
@Component
public class ContantRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        ExecutorService executorService = ThreadTool.getIntanst();
    }
}
