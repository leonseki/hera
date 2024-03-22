package jp.tokyo.leon.hera.dms.hanlder;

import jp.tokyo.leon.hera.dms.entity.SqlEntity;
import jp.tokyo.leon.hera.dms.enums.EventTypeEnum;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author longtao.guan
 */
@Service
@Order(2)
public class UpdateTypeRowSqlHandler extends AbstractRowSqlHandler{
    @Override
    boolean filterEventType(SqlEntity<Object> sqlEntity) {
        return sqlEntity.getEventType() == EventTypeEnum.UPDATE;
    }
}
