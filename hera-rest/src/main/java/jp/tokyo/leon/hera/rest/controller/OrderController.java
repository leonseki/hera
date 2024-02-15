package jp.tokyo.leon.hera.rest.controller;

import jp.tokyo.leon.hera.common.api.ResponseResult;
import jp.tokyo.leon.hera.dao.entity.Order;
import jp.tokyo.leon.hera.dao.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author leon
 * @date 2024/2/15 23:19
 */
@RestController
@RequestMapping("/v2/api/order")
public class OrderController {

    private OrderMapper orderMapper;

    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @PostMapping("")
    public ResponseResult createOrder(@RequestBody Order order) {
        orderMapper.insert(order);
        return ResponseResult.ok();
    }

}
