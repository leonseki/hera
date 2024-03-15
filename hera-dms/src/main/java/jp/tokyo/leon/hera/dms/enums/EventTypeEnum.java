package jp.tokyo.leon.hera.dms.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author longtao.guan
 */

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum EventTypeEnum {

    INSERT(1, "INSERT"),

    UPDATE(2, "UPDATE"),

    DELETE(3, "DELETE"),

    CREATE(4, "CREATE"),

    ALTER(5, "ALTER"),

    ERASE(6, "ERASE"),

    QUERY(7, "QUERY"),

    TRUNCATE(8, "TRUNCATE"),

    RENAME(9, "RENAME");

    private final int code;

    private final String name;

}
