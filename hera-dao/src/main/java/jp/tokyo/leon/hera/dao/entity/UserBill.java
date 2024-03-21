package jp.tokyo.leon.hera.dao.entity;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author leon
 * @date 2024/3/20 10:50
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserBill {
    private Long id;

    private Long userId;

    private Long brokerId;

    private Byte type;

    private BigDecimal size;

    private BigDecimal beforeBalance;

    private BigDecimal afterBalance;

    private BigDecimal fee;

    private Long referId;

    private Date createTime;

    private Date modifyTime;

}
