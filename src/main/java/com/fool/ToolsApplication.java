package com.fool;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fool.mapper")
// @EnableScheduling
// @EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class ToolsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ToolsApplication.class, args);
    }
}
