package by.logonuk.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class UserAspect {

    @Pointcut("execution(* by.logonuk.rest.user.UserControllerImpl.*(..)) && !execution(* by.logonuk.rest.user.UserControllerImpl.findAll(..))")
    public void accountControllerMethods() {}

    @Before("accountControllerMethods()")
    public void logMethodCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();

        Object[] args = joinPoint.getArgs();
        for (Object request : args) {
            log.info("[{}] --> {}", methodName, request);
        }
    }

    @AfterReturning(pointcut = "accountControllerMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        log.info("[{}] <-- {}", methodName, result);
    }
}
