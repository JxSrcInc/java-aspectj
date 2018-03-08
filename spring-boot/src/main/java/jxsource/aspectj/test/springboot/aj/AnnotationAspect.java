package jxsource.aspectj.test.springboot.aj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import jxsource.aspectj.trace.ThreadTrace;

@Aspect
public class AnnotationAspect {
        private ThreadTrace trace = new ThreadTrace();
        @Around("execution(* jxsource.aspectj.testcode..*(..))")
        public Object around(ProceedingJoinPoint jp) throws Throwable{
        	return trace.traceAround(jp);
        }

}
 