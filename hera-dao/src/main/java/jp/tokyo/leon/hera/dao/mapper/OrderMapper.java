package jp.tokyo.leon.hera.dao.mapper;

import java.util.List;
import jp.tokyo.leon.hera.dao.entity.Order;
import jp.tokyo.leon.hera.dao.interceptor.annotation.TableShard;
import jp.tokyo.leon.hera.dao.interceptor.strategy.TableShardStrategyUserId;

/**
 * @author leon
 */
@TableShard(originTableName = "tb_order", value = "userId", fieldFlag = true, shardStrategy = TableShardStrategyUserId.class)
public interface OrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    Order selectByPrimaryKey(Long id);

    List<Order> selectAll();

    int updateByPrimaryKey(Order record);
}