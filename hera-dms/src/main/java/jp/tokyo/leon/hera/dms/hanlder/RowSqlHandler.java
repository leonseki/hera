package jp.tokyo.leon.hera.dms.hanlder;

import com.alibaba.fastjson.JSONObject;
import jp.tokyo.leon.hera.dms.entity.SqlEntity;

/**
 * @author leon
 * @date 2024/3/20 10:34
 */
public interface RowSqlHandler {
    void parseRowSql(SqlEntity<Object> sqlEntity, JSONObject jsonObject);

    void executeSql(SqlEntity<Object> sqlEntity);
}
