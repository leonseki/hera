package jp.tokyo.leon.hera.dms.processor;

import jp.tokyo.leon.hera.dms.entity.SqlEntity;

/**
 * @author longtao.guan
 */
public abstract class SqlProcessor {

   public abstract SqlEntity parseSql(String sql);
}
