package jp.tokyo.leon.hera.dao.interceptor;

import jp.tokyo.leon.hera.dao.interceptor.annotation.TableShard;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;
import java.util.Objects;

/**
 * @author leon
 * @date 2024/2/15 22:28
 */
@Intercepts({
        @Signature(
                type = StatementHandler.class,
                method = "prepare",
                args = {Connection.class, Integer.class}
        )
})
@Component
public class TableShardInterceptor implements Interceptor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private static final ReflectorFactory defaultReflectorFactory = new DefaultReflectorFactory();
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MetaObject metaObject = getMetaObject(invocation);
        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        // 获取Mapper执行方法
        Method method = invocation.getMethod();

        TableShard tableShard = getTableShard(method, mappedStatement);
        if (Objects.isNull(tableShard)) {
            return invocation.proceed();
        }
        // 获取值
        String value = tableShard.value();
        // value是否是字段名，如果是需要解析
        boolean fieldFlag = tableShard.fieldFlag();

        if (fieldFlag) {
            // 获取请求参数
            Object parameterObject = boundSql.getParameterObject();
            if (parameterObject instanceof MapperMethod.ParamMap<?>) { //ParamMap类型逻辑处理
                MapperMethod.ParamMap paramMap = (MapperMethod.ParamMap) parameterObject;
                // 根据字段名获取参数值
                Object valueObject = paramMap.get(value);
                if (Objects.isNull(valueObject)) {
                    throw new RuntimeException(String.format("入参字段%s无匹配", value));
                }
                // 替换sql
                replaceSql(tableShard, valueObject, metaObject, boundSql);
            } else {
                // 单参数逻辑
                // 如果是基础类型抛出异常
                if (isBaseType(parameterObject)) {
                    throw new RuntimeException("单参数非法，请使用@param注解");
                }

                if (parameterObject instanceof Map) {
                    Map<String, Object> paramterMap = ((Map<String, Object>) parameterObject);
                    Object valueObject = paramterMap.get(value);
                    replaceSql(tableShard, valueObject, metaObject, boundSql);
                } else {
                    // 非基础类型对象
                    Class<?> parameterObjectClass = parameterObject.getClass();
                    Field declaredField = parameterObjectClass.getDeclaredField(value);
                    declaredField.setAccessible(true);
                    Object valueObject = declaredField.get(parameterObject);
                    replaceSql(tableShard, valueObject, metaObject, boundSql);
                }
            }
        } else {
            // 无需处理
            replaceSql(tableShard, value, metaObject, boundSql);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }


    private MetaObject getMetaObject(Invocation invocation) {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        return MetaObject.forObject(statementHandler,
                SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,
                defaultReflectorFactory
        );
    }

    private TableShard getTableShard(Method method, MappedStatement mappedStatement) throws ClassNotFoundException {
        String id = mappedStatement.getId();
        final String className = id.substring(0, id.lastIndexOf("."));
        TableShard tableShard;
        tableShard = method.getAnnotation(TableShard.class);
        if (Objects.isNull(tableShard)) {
            tableShard = Class.forName(className).getAnnotation(TableShard.class);
        }
        return tableShard;
    }

    private void replaceSql(TableShard tableShard, Object value, MetaObject metaObject, BoundSql boundSql) {
        String originTableName = tableShard.originTableName();
        Class<? extends TableShardStrategy> strategyClass = tableShard.shardStrategy();

        TableShardStrategy tableShardStrategy = applicationContext.getBean(strategyClass);
        String shardTableName = tableShardStrategy.generateTableName(originTableName, value);

        String sql = boundSql.getSql();
        metaObject.setValue("delegate.boundSql.sql", sql.replace(originTableName, shardTableName));

    }

    private boolean isBaseType(Object object) {
        return object.getClass().isPrimitive()
                || object instanceof String
                || object instanceof Integer
                || object instanceof Double
                || object instanceof Float
                || object instanceof Long
                || object instanceof Boolean
                || object instanceof Byte
                || object instanceof Short;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
