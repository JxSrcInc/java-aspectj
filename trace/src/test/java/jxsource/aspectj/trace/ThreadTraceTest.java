package jxsource.aspectj.trace;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import jxsource.aspectj.trace.ThreadTrace;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.CodeSignature;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class ThreadTraceTest {
	ThreadTrace threadTrace;
	CodeSignature signature;
	JoinPoint joinPoint;
	@Before
	public void init() {
		threadTrace = ThreadTraceLocal.get();
		signature = mock(CodeSignature.class);
		joinPoint = mock(JoinPoint.class);
	}
	
	@Test
	public void notNullTest() {
		assertNotNull(threadTrace);
	}
	
	@Test
	public void traceMethodEntryTest() {
	  when(joinPoint.getSignature()).thenReturn(signature);
      when(signature.getDeclaringTypeName()).thenReturn("testClass");
      when(signature.getName()).thenReturn("testMethod");
      when(signature.getParameterTypes()).thenReturn(new Class<?>[0]);

      threadTrace.traceMethodEntry(joinPoint, false);
      assertEquals(signature.getName(), "testMethod");
      assertEquals(signature.getDeclaringTypeName(), "testClass");
	}
}
