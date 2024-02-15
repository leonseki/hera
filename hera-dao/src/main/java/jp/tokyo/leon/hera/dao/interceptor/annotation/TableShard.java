package jp.tokyo.leon.hera.dao.interceptor.annotation;

import jp.tokyo.leon.hera.dao.interceptor.TableShardStrategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author leon
 * @date 2024/2/15 22:25
 */
@Target(value = {ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TableShard {
    // 原始表名
    String originTableName();

    String value() default "";

    boolean fieldFlag() default false;

    Class<? extends TableShardStrategy> shardStrategy();
}
