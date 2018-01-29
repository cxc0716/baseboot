package com.cxc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * author:chenxinchao date:2016-09-21 15:47 desc:com.cxc
 */
@SpringBootApplication
@EnableTransactionManagement
public class MyApplication extends SpringBootServletInitializer
    implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out
            .println("here can execute init operate once application is up");
    }
}
