package jp.tokyo.leon.hera.dms.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jp.tokyo.leon.hera.dms.entity.SqlEntity;
import jp.tokyo.leon.hera.dms.enums.EventTypeEnum;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author longtao.guan
 */
@Component
public class CanalSqlProcessor extends SqlProcessor{
    @Override
    public SqlEntity parseSql(String originData) throws RuntimeException{
        boolean ddl = false;
        JSONObject sqlObject = JSON.parseObject(originData);
        String schema = sqlObject.getString("database");
        if (!StringUtils.hasLength(schema)) {
            throw new RuntimeException("parse sql error, can not find schema.");
        }
        String table = sqlObject.getString("table");
        if (!StringUtils.hasLength(table)) {
            throw new RuntimeException("parse sql error, can not find table.");
        }
        String isDdl = sqlObject.getString("isDdl");
        ddl = Boolean.getBoolean(isDdl);
        String eventType = sqlObject.getString("type");
        if (!StringUtils.hasLength(eventType)) {
            throw new RuntimeException("parse sql error, can not find sql type.");
        }
        SqlEntity sqlEntity = new SqlEntity();
        sqlEntity.setSchema(schema);
        sqlEntity.setTable(table);
        sqlEntity.setEventType(EventTypeEnum.valueOf(eventType));
        sqlEntity.setDdl(ddl);



        return sqlEntity;
    }

}
