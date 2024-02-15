package jp.tokyo.leon.hera.dao.entity;

import lombok.*;

import java.util.Date;

/**
 * @author lone
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {

    private Long id;

    private Long userId;

    private Long amount;

    private Date createTime;

    private Date modifyTime;

}