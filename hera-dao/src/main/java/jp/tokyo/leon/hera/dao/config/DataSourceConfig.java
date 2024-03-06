package jp.tokyo.leon.hera.dao.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author leon
 * @date 2024/2/17 19:15
 */
@Configuration
public class DataSourceConfig {


    @ConfigurationProperties(prefix = "spring.datasource.write")
    public DataSource dataSourceWrite() {
        return DruidDataSourceBuilder.create().build();
    }

    @ConfigurationProperties(prefix = "spring.datasource.read")
    public DataSource dataSourceRead() {
        return DruidDataSourceBuilder.create().build();
    }
}
