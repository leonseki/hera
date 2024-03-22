package jp.tokyo.leon.hera.dms.hanlder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jp.tokyo.leon.hera.common.enums.CanalErrorCode;
import jp.tokyo.leon.hera.common.exception.CanalProcessException;
import jp.tokyo.leon.hera.dms.entity.SqlEntity;
import jp.tokyo.leon.hera.dms.util.CanalUtils;
import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author longtao.guan
 */
public abstract class AbstractRowSqlHandler implements RowSqlHandler{

    @Value("${db.canal.entity.path.prefix: jp.tokyo.leon.hera.dao.entity}")
    private String entityPathPrefix;
    @Override
    public void parseRowSql(SqlEntity<Object> sqlEntity, JSONObject jsonObject) {
        if (!filterEventType(sqlEntity)) {
            return;
        }
        fillEventTypeData(sqlEntity, jsonObject);
    }

    private void fillEventTypeData(SqlEntity<Object> sqlEntity, JSONObject jsonObject) {
        JSONArray data = jsonObject.getJSONArray("data");
        if(data.isEmpty()) {
            return;
        }
        List<Object> entityList = new ArrayList<>();
        try {
            Class<?> aClass = Class.forName(entityPathPrefix + "." + CanalUtils.convertTableNameToEntityName(sqlEntity.getTable()));
            Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
            Object o = declaredConstructor.newInstance();
            for (int i = 0; i < data.size(); i++) {
                JSONObject object = data.getJSONObject(i);
                Object parse = JSON.parseObject(object.toJSONString(), o.getClass());
                entityList.add(parse);
            }
            sqlEntity.setEntity(entityList);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException |
                 InvocationTargetException e) {
            throw new CanalProcessException(CanalErrorCode.PARSE_OBJECT_ERROR);
        }
    }

    abstract boolean filterEventType(SqlEntity<Object> sqlEntity);

}
