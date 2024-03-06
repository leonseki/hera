package jp.tokyo.leon.hera.util;



/**
 * @author leon
 * @date 2024/3/6 22:30
 */
public interface FileTypeHandler<T, R> {
    R transferFileType(T t);
}
