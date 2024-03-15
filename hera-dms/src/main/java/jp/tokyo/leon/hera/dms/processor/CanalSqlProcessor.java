package jp.tokyo.leon.hera.dms.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jp.tokyo.leon.hera.dms.entity.SqlEntity;
import jp.tokyo.leon.hera.dms.enums.EventTypeEnum;
import org.springframework.stereotype.Component;

/**
 * @author longtao.guan
 */
@Component
public class CanalSqlProcessor extends SqlProcessor{
    @Override
    public SqlEntity parseSql(String originData) {
        JSONObject sqlObject = JSON.parseObject(originData);

        System.out.println(originData);
        return null;
    }

    private EventTypeEnum parseEventType(JSONObject jsonObject) {
        
    }
}
