package jp.tokyo.leon.hera.common.exception;

import jp.tokyo.leon.hera.common.enums.CanalErrorCode;
import lombok.Getter;

import java.io.Serial;

/**
 * @author longtao.guan
 */
@Getter
public class CanalProcessException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -6495138957558478969L;

    private final int code;

    public CanalProcessException() {
        super();
        this.code = -1;
    }

    public CanalProcessException(String message) {
        super(message);
        this.code = -1;
    }

    public CanalProcessException(CanalErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public CanalProcessException(CanalErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.code = -1;
    }
}
