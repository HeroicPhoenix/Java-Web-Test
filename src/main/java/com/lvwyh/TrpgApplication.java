package com.lvwyh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lvwyh.mapper")
public class TrpgApplication {
    public static void main(String[] args) {
        SpringApplication.run(TrpgApplication.class, args);
    }
}
