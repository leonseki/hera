package jp.tokyo.leon.hera.dms.hanlder;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jp.tokyo.leon.hera.dms.entity.SqlEntity;
import jp.tokyo.leon.hera.dms.enums.EventTypeEnum;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author leon
 * @date 2024/3/20 10:35
 */
@Service
@Order(1)
public class InsertTypeRowSqlHandler implements RowSqlHandler{

    @Override
    public void parseRowSql(SqlEntity<Object> sqlEntity, JSONObject jsonObject) {
        if (sqlEntity.getEventType() != EventTypeEnum.INSERT) {
            return;
        }
        JSONArray data = jsonObject.getJSONArray("data");
        JSONObject object = data.getJSONObject(0);
        try {
            Class<?> aClass = Class.forName("jp.tokyo.leon.hera.dao.entity.UserBill");
            Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
            Object o = declaredConstructor.newInstance();
            Object parse = JSONObject.parseObject(object.toJSONString(), o.getClass());
            sqlEntity.setEntity(parse);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        System.out.println(object.entrySet());
        System.out.println(sqlEntity);
    }

    @Override
    public void executeSql(SqlEntity<Object> sqlEntity) {
        
    }
}
