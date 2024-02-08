package com.mrurespect.employeeapp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect extends PointCutExpression {
    private final Logger logger =Logger.getLogger(getClass().getName());

    @Before("appFlow()")
    public void beforeAdvice(JoinPoint joinPoint){

        //display method we are calling
        String method =joinPoint.getSignature().toShortString();
        logger.info(() -> "====>> in @BeforeAdvice: calling method " + method);
        // display the arguments
        Object[] objects =joinPoint.getArgs();
        for (Object arg:objects) {
            logger.info(() -> "====>> argument: " + arg);
        }
    }

    @AfterReturning(
            pointcut = "appFlow()",
            returning = "result"
    )
    public void afterReturningAdvice(JoinPoint joinPoint,Object result){
        //display method we are calling
        String method =joinPoint.getSignature().toShortString();
        logger.info(() -> "====>> in @AfterReturning: calling method " + method);

        //display data returned
        logger.info(() -> "====>> result: " + result);
    }
    @AfterThrowing(
            pointcut = "appFlow()",
            throwing = "exception"
    )
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception exception) {
        String method = joinPoint.getSignature().toShortString();
        logger.severe("====>> Exception in " + method + ": " + exception.getMessage());
    }


}
