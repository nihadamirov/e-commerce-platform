package com.euphoria_ecommerce.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.StopWatch;

@Aspect
@Slf4j
public class LoggingAspect {

    @Pointcut("within(com.euphoria_ecommerce.controller..*)" +
            " || within(com.euphoria_ecommerce.service..*)")
    public void applicationPackagePointcut() {
    }

    @AfterReturning(pointcut = "applicationPackagePointcut()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        log.info("Executed method: {}. Result: {}", joinPoint.getSignature().toShortString(), result);
    }

    @AfterThrowing(pointcut = "applicationPackagePointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {

        log.error("Exception in {}.{}() with cause = '{}' and exception = '{}'",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                getExceptionCause(e), e.getMessage());
    }

    private Object getExceptionCause(Throwable throwable) {
        return throwable.getCause() != null ? throwable.getCause() : "NULL";
    }

    @Around("applicationPackagePointcut()")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Executed method: {}", proceedingJoinPoint.getSignature().toShortString());
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();
        final long executionTime = stopWatch.getTotalTimeMillis();
        final String methodName = proceedingJoinPoint.getSignature().getName();
        final String className = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        log.info("Execution time {} ms for method: {} in class: {}",
                executionTime, methodName, className);
        return result;
    }
}