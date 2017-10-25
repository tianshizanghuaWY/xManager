package com.qianyang;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.qianyang.config.Config;

import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] a) throws InterruptedException {

        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(Config.class);

        //UseFunctionService service = context.getBean(UseFunctionService.class);
        //System.out.println(service.sayHello("spring_4"));

//        CountDownLatch latch = new CountDownLatch(1);
//        latch.await();

        context.close();
    }

    //AnnotationConfigApplicationContext 作为spring 容器
}
