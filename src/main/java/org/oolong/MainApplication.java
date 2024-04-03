package org.oolong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("org.oolong.mapper")
@SpringBootApplication
public class MainApplication {
    
    public static void main(String[] args) {
        
        SpringApplication.run(MainApplication.class, args);
        
        System.out.println("Hello world!");
    }
}