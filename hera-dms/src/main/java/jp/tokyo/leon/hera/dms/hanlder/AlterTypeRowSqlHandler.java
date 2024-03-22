package jp.tokyo.leon.hera.dms.hanlder;

import com.alibaba.fastjson.JSONObject;
import jp.tokyo.leon.hera.dms.entity.SqlEntity;
import jp.tokyo.leon.hera.dms.enums.EventTypeEnum;
import jp.tokyo.leon.hera.dms.util.CanalUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author longtao.guan
 */
@Component
@Order(3)
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
        String sql = CanalUtils.removeComment(sqlEntity.getOriginSqlString());
        sqlEntity.setRowSql(sql);
    }
}
