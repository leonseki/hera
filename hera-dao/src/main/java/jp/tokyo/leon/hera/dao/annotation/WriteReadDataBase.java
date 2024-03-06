package jp.tokyo.leon.hera.dao.annotation;

import jp.tokyo.leon.hera.dao.enums.WriteReadTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author leon
 * @date 2024/2/17 19:22
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface WriteReadDataBase {
    WriteReadTypeEnum value() default WriteReadTypeEnum.WRITE;
}
