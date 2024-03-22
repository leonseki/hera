package jp.tokyo.leon.hera.dao.mapper;

import jp.tokyo.leon.hera.dao.entity.UserBill;

import java.util.List;

/**
 * @author leon
 * @date 2024/3/20 12:28
 */
public interface UserBillMapper extends BaseMapper{

    @Override
    int insert(List<Object> entities);

    @Override
    int update(List<Object> entities);

    @Override
    void alter(String rowSql);
}
