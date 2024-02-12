package jp.tokyo.leon.hera.rest.aspect;

import jakarta.servlet.http.HttpServletRequest;
import jp.tokyo.leon.hera.common.api.ApiLog;
import jp.tokyo.leon.hera.rest.annotation.ApiOperationLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author leon
 * @date 2024/2/12 22:33
 */
@Component
@Aspect
@Order(1)
@Slf4j
public class ApiLogAspect {
    /**
     * 日志记录
     * 环绕通知
     */

    /**
     * 定义一个切点
     */
    @Pointcut("execution(* jp.tokyo.leon.hera.rest.controller..*.*(..))") // controller包里所有类，类里面所有方法
    public void logPointCut(){}


    @Around("logPointCut()")
    public Object recordLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 先获取方法注解，没有日志注解不记录日志
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        ApiOperationLog annotation = method.getAnnotation(ApiOperationLog.class);
        if (Objects.isNull(annotation)) {
            return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        }

        ApiLog apiLog = new ApiLog();

        long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        long end = System.currentTimeMillis();

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        String url = request.getRequestURL().toString();
        apiLog.setSpendTime((int)(start - end) / 1000);
        apiLog.setUri(request.getRequestURI());
        apiLog.setUrl(url);
        apiLog.setDescription(annotation.value());

        log.info("{}", apiLog);

        return result;

    }

}
