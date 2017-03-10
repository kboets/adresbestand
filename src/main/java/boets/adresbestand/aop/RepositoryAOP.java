package boets.adresbestand.aop;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Asus on 6/03/2017.
 */
@Aspect
@Component
public class RepositoryAOP {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(boets.adresbestand.repository.PersonRepository)")
    public void pointCutClass(){
    }

    @AfterThrowing(pointcut = "pointCutClass()", throwing = "t")
    public void handleConstraintViolationException(ProceedingJoinPoint joinPoint, Throwable t){
        logger.info("entering ");

    }
}
