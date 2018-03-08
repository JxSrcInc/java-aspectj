package jxsource.aspectj.test.weavejar.aj;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import jxsource.aspectj.trace.ThreadTrace;

@Aspect
public class AnnotationAspect {
        private ThreadTrace trace = new ThreadTrace();
        @Around("execution(* jxsource.aspectj.testcode..*(..))")
        public Object around(ProceedingJoinPoint jp) throws Throwable{
        	return trace.traceAround(jp);
        }

}
 