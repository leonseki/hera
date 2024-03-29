package jp.tokyo.leon.hera.dms.executor;

import jp.tokyo.leon.hera.dao.mapper.BaseMapper;
import jp.tokyo.leon.hera.dms.entity.SqlEntity;
import jp.tokyo.leon.hera.dms.enums.EventTypeEnum;
import jp.tokyo.leon.hera.dms.util.CanalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author longtao.guan
 */
@Component
@Order(1)
public class InsertTypeSqlExecutor implements SqlExecutor{
    private final Map<String, BaseMapper> mapperMapping;

    @Autowired
    public InsertTypeSqlExecutor(Map<String, BaseMapper> mapperMapping) {
        this.mapperMapping = mapperMapping;
    }

    @Override
    public void execute(SqlEntity<Object> sqlEntity) {
        if (sqlEntity.getEventType() != EventTypeEnum.INSERT) {
            return;
        }
        String tableName = sqlEntity.getTable();
        String mapperName = CanalUtils.convertTableNameToMapperName(tableName);

        BaseMapper mapper = mapperMapping.get(mapperName);
        mapper.insert(sqlEntity.getEntity());
    }
}
