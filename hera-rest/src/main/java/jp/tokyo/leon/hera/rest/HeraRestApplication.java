package jp.tokyo.leon.hera.rest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;


/**
 * @author leon
 * @date 2024/2/12 21:24
 */
@SpringBootApplication
@ComponentScan(basePackages = {"jp.tokyo.leon.hera.service", "jp.tokyo.leon.hera.rest", "jp.tokyo.leon.hera.dao"})
@MapperScan(basePackages = {"jp.tokyo.leon.hera.dao.mapper"})
public class HeraRestApplication {
    public static void main(String[] args) {
        /*
         * 注意点1：项目中同时使用了 mybatis 和 jpa 注意它们包扫描的位置，不能有交叉不然创建bean时会有交叉
         *
         * org.springframework.context.annotation.ConflictingBeanDefinitionException:
         * Annotation-specified bean name 'studentRepository' for bean class [jp.tokyo.leon.hera.dao.repository.StudentRepository]
         * conflicts with existing, non-compatible bean definition of same name and class
         * [org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean]

         */
        SpringApplication.run(HeraRestApplication.class, args);
    }
}
