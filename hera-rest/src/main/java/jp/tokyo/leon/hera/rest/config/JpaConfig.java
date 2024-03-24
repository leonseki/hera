package jp.tokyo.leon.hera.rest.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author leon
 * @date 2024/3/24 16:54
 */
@Configuration
@EnableJpaRepositories(basePackages = {"jp.tokyo.leon.hera.dao.repository"})
@EntityScan(basePackages = "jp.tokyo.leon.hera.dao.entity")
public class JpaConfig {
}
