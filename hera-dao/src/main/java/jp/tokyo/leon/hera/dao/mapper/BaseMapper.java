package jp.tokyo.leon.hera.dao.mapper;

import java.util.List;

/**
 * @author longtao.guan
 */
public interface BaseMapper {
    int insert(List<Object> entities);

    int update(List<Object> entities);

    void alter(String rowSql);
}
