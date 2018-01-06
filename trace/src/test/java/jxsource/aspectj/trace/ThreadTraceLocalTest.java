package jxsource.aspectj.trace;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import jxsource.aspectj.trace.ThreadTrace;
import jxsource.aspectj.trace.ThreadTraceLocal;

public class ThreadTraceLocalTest {
	
	@Test
	public void initTest() {
		assertTrue(ThreadTraceLocal.get() instanceof ThreadTrace);
	}
	
	@Test 
	public void multiGetTest() {
		assertTrue(ThreadTraceLocal.get().hashCode()==ThreadTraceLocal.get().hashCode());
	}
	
	@Test 
	public void resetTest() {
		ThreadTrace fetchedThreadTrace = ThreadTraceLocal.get();
		ThreadTraceLocal.reset();
		assertNotEquals(ThreadTraceLocal.get(), fetchedThreadTrace);
	}
	
	@Test
	public void setTest() {
		ThreadTrace threadTrace = new ThreadTrace();
		ThreadTraceLocal.set(threadTrace);
		assertEquals(ThreadTraceLocal.get(), threadTrace);
	}

}
