package jxsource.aspectj.trace.aj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AnnotationAspect {
//    @Around("execution(void jxsource.aspectj.trace.ThreadTrace.traceMethodEntry(Signature, boolean, String)) && args(number)")
//    public Object intercept(final ProceedingJoinPoint thisJoinPoint, int number) throws Throwable {
//        System.out.println(thisJoinPoint + " -> " + number);
//        if (number < 0)
//            return thisJoinPoint.proceed(new Object[] { -number });
//        if (number > 99)
//            throw new RuntimeException("oops");
//        return thisJoinPoint.proceed();
//    }
    
    @Before("execution(void jxsource.aspectj.trace.ThreadTrace.traceMethodEntry(Signature, boolean)) && "
    		+ "args(signature, showStack)")
    public void before(Signature signature, boolean showStack) {
        System.out.println("###### "+signature.getDeclaringTypeName() + ", " + showStack);
    }

}