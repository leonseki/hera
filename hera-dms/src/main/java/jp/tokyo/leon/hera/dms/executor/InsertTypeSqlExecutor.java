package jp.tokyo.leon.hera.dms.executor;

import jp.tokyo.leon.hera.dms.entity.SqlEntity;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author longtao.guan
 */
@Service
@Order(1)
public class InsertTypeSqlExecutor implements SqlExecutor{
    @Override
    public void execute(SqlEntity<Object> sqlEntity) {

    }
}
