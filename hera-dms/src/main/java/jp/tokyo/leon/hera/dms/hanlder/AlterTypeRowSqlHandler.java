package jp.tokyo.leon.hera.dms.hanlder;

import com.alibaba.fastjson.JSONObject;
import jp.tokyo.leon.hera.dms.entity.SqlEntity;
import jp.tokyo.leon.hera.dms.enums.EventTypeEnum;

/**
 * @author longtao.guan
 */
public class AlterTypeRowSqlHandler extends AbstractRowSqlHandler{
    @Override
    boolean filterEventType(SqlEntity<Object> sqlEntity) {
        return sqlEntity.getEventType() == EventTypeEnum.ALTER;
    }

    @Override
    public void parseRowSql(SqlEntity<Object> sqlEntity, JSONObject jsonObject) {
        if (!filterEventType(sqlEntity)) {
            return;
        }

    }
}
