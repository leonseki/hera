package jp.tokyo.leon.hera.dms.hanlder;

import jp.tokyo.leon.hera.dms.entity.SqlEntity;
import jp.tokyo.leon.hera.dms.enums.EventTypeEnum;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author leon
 * @date 2024/3/20 10:35
 */
@Component
@Order(1)
public class InsertTypeRowSqlHandler extends AbstractRowSqlHandler{

    @Override
    boolean filterEventType(SqlEntity<Object> sqlEntity) {
        return sqlEntity.getEventType() == EventTypeEnum.INSERT;
    }

}
