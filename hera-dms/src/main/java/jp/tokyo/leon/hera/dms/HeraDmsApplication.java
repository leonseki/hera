package jp.tokyo.leon.hera.dms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author longtao.guan
 */
@SpringBootApplication
@MapperScan(basePackages = {"jp.tokyo.leon.hera.dao.mapper"})
public class HeraDmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(HeraDmsApplication.class, args);
    }
}
