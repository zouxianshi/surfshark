package com.zxs.surfshark;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@MapperScan(basePackages = "com.zxs.surfshark.dao")
@Slf4j
public class SurfSharkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SurfSharkApplication.class, args);
    }

}
