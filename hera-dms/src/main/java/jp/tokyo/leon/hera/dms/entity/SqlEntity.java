package jp.tokyo.leon.hera.dms.entity;

import jp.tokyo.leon.hera.dms.enums.EventTypeEnum;
import lombok.Data;

import java.util.List;

/**
 * @author longtao.guan
 */
@Data
public class SqlEntity<T> {

    private String schema;

    private String table;

    private boolean ddl;

    private EventTypeEnum eventType;

    private String originSqlString;

    private String rowSql;

    private List<T> entity;
}
