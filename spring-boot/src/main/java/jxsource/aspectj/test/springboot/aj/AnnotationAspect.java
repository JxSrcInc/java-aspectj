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
// TODO: log4j does not work with other slf4j binding
//        private static final Logger logger = LogManager
//                        .getLogger(AnnotationAspect.class);
        private ThreadTrace trace = new ThreadTrace();
        @AfterReturning(value = "execution(* jxsource.aspectj.testcode..*(..))", returning = "retVal")
        public void info(JoinPoint jp, Object retVal) {
        	trace.traceMethodExit(jp, retVal);
        }
 
        @Before(value = "execution(* jxsource.aspectj.testcode..*(..))")
        public void test(JoinPoint jp) {
        	trace.traceMethodEntry(jp);
        }
        @AfterThrowing(value = "execution(* jxsource.aspectj.testcode..*(..))", throwing = "e")
        public void throwing(JoinPoint jp, Throwable e) {
        	trace.traceThrowable(jp, e);
        }

}
 