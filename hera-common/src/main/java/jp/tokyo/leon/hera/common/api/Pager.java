package jp.tokyo.leon.hera.common.api;

import lombok.*;

/**
 * @author leon
 * @date 2024/2/12 21:42
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Pager<T> {

    private Long current;

    private Long pageSize;

    private T rows;
}
