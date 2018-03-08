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
import jxsource.aspectj.trace.ThreadTrace;
import jxsource.aspectj.trace.ThreadTraceLocal;
import jxsource.aspectj.trace.LocalThreadManager;

@Aspect
public class AnnotationAspect {
	ThreadTrace trace = ThreadTraceLocal.get().setShowTime(true);
//    @AfterReturning(value = "execution(* jxsource.aspectj.trace.TestClass.*(..))", returning = "retVal")
//    public void info(JoinPoint jp, Object retVal) {
//    	trace.traceMethodExit(jp, retVal);
//    	LocalThreadManager.get().add(Thread.currentThread().getName()+"-after-"+jp.getSignature().toString(), retVal.toString());
//    }
//    
//    @Before("execution(* jxsource.aspectj.trace.TestClass.*(..))")
//    public void before(JoinPoint jp) {
//    	trace.traceMethodEntry(jp);
//    	LocalThreadManager.get().add(Thread.currentThread().getName()+"-before-"+jp.getSignature().toString(), jp.getSignature().toString());
//    }
//
//    @AfterThrowing(value = "execution(* jxsource.aspectj.trace.TestClass.*(..))", throwing = "e")
//    public void throwing(JoinPoint jp, Throwable e) {
//    	trace.traceThrowable(jp, e);
//    	LocalThreadManager.get().add(Thread.currentThread().getName()+"-throwing-"+jp.getSignature().toString(), e.toString());
//    }
    @Around("execution(* jxsource.aspectj.trace.TestClass.*(..))")
    public Object around(ProceedingJoinPoint jp) throws Throwable{
    	LocalThreadManager.get().add(Thread.currentThread().getName()+"-before-"+jp.getSignature().toString(), jp.getSignature().toString());
    	return trace.traceAround(jp);
    }

}