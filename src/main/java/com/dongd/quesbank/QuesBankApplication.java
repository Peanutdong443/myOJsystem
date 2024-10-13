package com.dongd.quesbank;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
@MapperScan("com.dongd.quesbank.*")
@EnableAspectJAutoProxy
public class QuesBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuesBankApplication.class, args);
    }

}
