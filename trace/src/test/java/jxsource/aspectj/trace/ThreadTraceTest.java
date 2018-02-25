package jxsource.aspectj.trace;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import jxsource.aspectj.trace.ThreadTrace;

import org.aspectj.lang.Signature;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class ThreadTraceTest {
	ThreadTrace threadTrace;
	Signature signature;
	@Before
	public void init() {
		threadTrace = new ThreadTrace();
		signature = mock(Signature.class);
	}
	
	@Test
	public void notNullTest() {
		assertNotNull(threadTrace);
	}
	
	@Test
	public void traceMethodEntryTest() {
      when(signature.getDeclaringTypeName()).thenReturn("testClass");
      when(signature.getName()).thenReturn("testMethod");

		threadTrace.traceMethodEntry(signature, false);
	}
}
