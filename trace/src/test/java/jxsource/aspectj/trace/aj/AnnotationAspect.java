package jxsource.aspectj.trace.aj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import jxsource.aspectj.trace.ThreadManager;
import jxsource.aspectj.trace.LocalThreadManager;

@Aspect
public class AnnotationAspect {
    @AfterReturning(value = "execution(* jxsource.aspectj.trace.TestObject.*(..))", returning = "retVal")
    public void info(JoinPoint jp, Object retVal) {
    	LocalThreadManager.get().add(Thread.currentThread().getName()+"-after-"+jp.getSignature().toString(), retVal.toString());
    }
    
    @Before("execution(* jxsource.aspectj.trace.TestObject.*(..))")
    public void before(JoinPoint jp) {
    	LocalThreadManager.get().add(Thread.currentThread().getName()+"-before-"+jp.getSignature().toString(), jp.getSignature().toString());
    }

    @AfterThrowing(value = "execution(* jxsource.aspectj.trace.TestObject.*(..))", throwing = "e")
    public void info(JoinPoint jp, Throwable e) {
    	LocalThreadManager.get().add(Thread.currentThread().getName()+"-throwing-"+jp.getSignature().toString(), e.toString());
    }

}