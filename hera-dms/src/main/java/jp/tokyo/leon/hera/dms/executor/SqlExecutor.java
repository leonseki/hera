package jp.tokyo.leon.hera.dms.executor;

import jp.tokyo.leon.hera.dms.entity.SqlEntity;

/**
 * @author longtao.guan
 */
public interface SqlExecutor {
    void execute(SqlEntity<Object> sqlEntity);
}
