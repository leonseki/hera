package jp.tokyo.leon.hera.dao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author leon
 * @date 2024/2/17 19:24
 */
@Getter
@AllArgsConstructor
public enum WriteReadTypeEnum {

    WRITE("WRITE"),

    READ("READ");

    private final String code;

}
