package jp.tokyo.leon.hera.dms.entity;

import jp.tokyo.leon.hera.dms.enums.EventTypeEnum;
import lombok.Data;

/**
 * @author longtao.guan
 */
@Data
public class SqlEntity<T> {

    private String schema;

    private String table;

    private boolean ddl;

    private EventTypeEnum eventType;

    private String rowSql;

    private T entity;
}
