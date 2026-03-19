package com.project.ridebooking.rideApp.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Value("${app.logging.enabled:true}")  // Configurable via application.properties
    private boolean loggingEnabled;

    // Reusable pointcut for all service implementation methods
    @Pointcut("execution(* com.project.ridebooking.rideApp.services.Impl.*.*(..))")
    public void serviceMethods() {}

    @Before("serviceMethods()")
    public void logBeforeMethodExecution(JoinPoint joinPoint) {
        if (!loggingEnabled || !log.isInfoEnabled()) return;
        
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        
        // Avoid logging sensitive data (e.g., passwords)
        String argsString = maskSensitiveArgs(args);
        
        log.info("Entering method: {} with arguments: {}", methodName, argsString);
    }

    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logAfterMethodExecution(JoinPoint joinPoint, Object result) {
        if (!loggingEnabled || !log.isInfoEnabled()) return;
        
        String methodName = joinPoint.getSignature().toShortString();
        log.info("Exiting method: {} with result: {}", methodName, result != null ? result.toString() : "null");
    }

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        if (!loggingEnabled || !log.isErrorEnabled()) return;
        
        String methodName = joinPoint.getSignature().toShortString();
        log.error("Exception in method: {} with message: {}", methodName, exception.getMessage(), exception);
    }

    @Around("serviceMethods()")
    public Object logAroundMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!loggingEnabled) return joinPoint.proceed();
        
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        try {
            Object result = joinPoint.proceed();
            stopWatch.stop();
            log.info("Method {} executed in {} ms", joinPoint.getSignature().toShortString(), stopWatch.getTotalTimeMillis());
            return result;
        } catch (Throwable throwable) {
            stopWatch.stop();
            log.error("Method {} failed after {} ms with exception: {}", joinPoint.getSignature().toShortString(), stopWatch.getTotalTimeMillis(), throwable.getMessage());
            throw throwable;
        }
    }

    // Helper method to mask sensitive arguments (e.g., passwords)
    private String maskSensitiveArgs(Object[] args) {
        if (args == null || args.length == 0) return "[]";
        
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < args.length; i++) {
            if (args[i] != null && args[i].toString().toLowerCase().contains("password")) {
                sb.append("***MASKED***");
            } else {
                sb.append(args[i].toString());
            }
            if (i < args.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}