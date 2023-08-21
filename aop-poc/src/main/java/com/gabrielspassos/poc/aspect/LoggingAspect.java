package com.gabrielspassos.poc.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("@annotation(com.gabrielspassos.poc.annotation.MyCustomLog)")
    public void logPointcut() {
    }

    @Before("logPointcut()")
    public void beforeLogPointCut() {
        LOGGER.info("In Aspect - Before execution");
    }

    @After("logPointcut()")
    public void AfterLogPointCut() {
        LOGGER.info("In aspect - After execution");
    }
}
