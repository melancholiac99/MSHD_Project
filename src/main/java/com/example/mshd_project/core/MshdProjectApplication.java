package com.example.mshd_project.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mshd_project.core.dao")
public class MshdProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MshdProjectApplication.class, args);
    }

}
