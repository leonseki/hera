package jp.tokyo.leon.hera.dms.executor;

import jp.tokyo.leon.hera.dao.mapper.BaseMapper;
import jp.tokyo.leon.hera.dms.entity.SqlEntity;
import jp.tokyo.leon.hera.dms.enums.EventTypeEnum;
import jp.tokyo.leon.hera.dms.util.CanalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author leon
 * @date 2024/3/22 21:51
 */
@Component
public class AlterTypeSqlExecutor implements SqlExecutor{

    private final Map<String, BaseMapper> mapperMapping;

    @Autowired
    public AlterTypeSqlExecutor(Map<String, BaseMapper> mapperMapping) {
        this.mapperMapping = mapperMapping;
    }
    @Override
    public void execute(SqlEntity<Object> sqlEntity) {
        if (sqlEntity.getEventType() != EventTypeEnum.ALTER) {
            return;
        }
        String tableName = sqlEntity.getTable();
        String mapperName = CanalUtils.convertTableNameToMapperName(tableName);

        BaseMapper mapper = mapperMapping.get(mapperName);
        mapper.alter(sqlEntity.getRowSql());
    }
}
