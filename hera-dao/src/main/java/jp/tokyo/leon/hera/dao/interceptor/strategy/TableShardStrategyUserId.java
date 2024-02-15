package jp.tokyo.leon.hera.dao.interceptor.strategy;

import jp.tokyo.leon.hera.dao.interceptor.TableShardStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author leon
 * @date 2024/2/15 22:19
 */
@Component
public class TableShardStrategyUserId implements TableShardStrategy {
    @Override
    public String generateTableName(String originTableName, Object value) {
        verifyTableNameSuffix(originTableName);
        if (!StringUtils.hasLength(value.toString())) {
            throw new RuntimeException("value is null");
        }
        long userId = Long.parseLong(value.toString());
        return originTableName + "_" + (userId % 4);
    }
}
