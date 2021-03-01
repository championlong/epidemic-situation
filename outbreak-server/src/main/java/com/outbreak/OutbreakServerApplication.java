package com.outbreak;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling//开启定时任务
@MapperScan("com.outbreak.mapper")
public class OutbreakServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OutbreakServerApplication.class, args);
    }
}
