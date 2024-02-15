package jp.tokyo.leon.hera.dao.interceptor;

import org.springframework.util.StringUtils;

/**
 * @author leon
 * @date 2024/2/15 22:13
 */
public interface TableShardStrategy {

    String generateTableName(String originTableName, Object value);

    default void verifyTableNameSuffix(String originTableName) {
        if (!StringUtils.hasLength(originTableName)) {
            throw new RuntimeException("table name suffix is null");
        }
    }
}
