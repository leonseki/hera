package jp.tokyo.leon.hera.rest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author leon
 * @date 2024/2/12 21:24
 */
@SpringBootApplication
@ComponentScan(basePackages = {"jp.tokyo.leon.hera.service", "jp.tokyo.leon.hera.rest", "jp.tokyo.leon.hera.dao"})
@MapperScan(basePackages = {"jp.tokyo.leon.hera.dao"})
public class HeraRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(HeraRestApplication.class, args);
    }
}
