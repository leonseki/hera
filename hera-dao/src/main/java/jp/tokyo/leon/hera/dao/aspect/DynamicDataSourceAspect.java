package jp.tokyo.leon.hera.dao.aspect;

import jp.tokyo.leon.hera.dao.annotation.WriteReadDataBase;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author leon
 * @date 2024/2/17 19:30
 */
@Aspect
public class DynamicDataSourceAspect {

    @Before("within(jp.tokyo.leon.hera.service.impl) && @annotation(writeReadDataBase)")
    public void before(WriteReadDataBase writeReadDataBase) {
        String name = writeReadDataBase.value().getCode();

    }
}
