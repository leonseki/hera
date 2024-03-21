package jp.tokyo.leon.hera.dms.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jp.tokyo.leon.hera.dms.entity.SqlEntity;
import jp.tokyo.leon.hera.dms.enums.EventTypeEnum;
import jp.tokyo.leon.hera.dms.hanlder.RowSqlHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author longtao.guan
 */
@Component
public class CanalSqlProcessor extends SqlProcessor{

    private final List<RowSqlHandler> handlers;

    @Autowired
    public CanalSqlProcessor(List<RowSqlHandler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public SqlEntity<Object> parseSql(String originData) throws RuntimeException{
        boolean ddl;
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
        SqlEntity<Object> sqlEntity = new SqlEntity<>();
        sqlEntity.setSchema(schema);
        sqlEntity.setTable(table);
        sqlEntity.setEventType(EventTypeEnum.valueOf(eventType));
        sqlEntity.setDdl(ddl);

        for (RowSqlHandler sqlHandler : handlers) {
            sqlHandler.parseRowSql(sqlEntity, sqlObject);
        }

        return sqlEntity;
    }

}
