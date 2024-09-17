package com.dongd.quesbank;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@SpringBootApplication
@MapperScan("com.dongd.quesbank.*")
@EnableAspectJAutoProxy
public class QuesBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuesBankApplication.class, args);
    }

}
