package jxsource.aspectj.springboot.app.aspect;

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
import org.springframework.stereotype.Component;

import jxsource.aspectj.springboot.app.bean.TestCode;
import jxsource.aspectj.trace.ThreadTrace;
import jxsource.aspectj.trace.ThreadTraceLocal;

@Aspect
@Component
public class AnnotationAspect {
	private static Logger log = LogManager.getLogger(AnnotationAspect.class);
	private ThreadTrace trace = ThreadTraceLocal.get();

	@Around("execution(* jxsource.aspectj.springboot.app.bean..*(..))")
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		log.info(jp.getSignature());
		return trace.traceAround(jp);
	}

	// @Before("execution(* jxsource.aspectj.springboot.app.bean..*(..))")
	// public void around(JoinPoint jp) throws Throwable{
	// log.info(jp.getSignature());
	// trace.traceMethodEntry(jp);
	// }

}
