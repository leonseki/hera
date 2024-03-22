package jp.tokyo.leon.hera.common.enums;

/**
 * @author longtao.guan
 */

public enum CanalErrorCode implements ErrorCode {

    PARSE_OBJECT_ERROR(10001,"canal message convert to entity failed");

    private final int code;

    private final String message;

    CanalErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
