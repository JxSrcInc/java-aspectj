package jxsource.aspectj.trace;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

public class ThreadTraceAspectTest {

	@Test
	public void testGet() {
		LocalThreadManager.get().reset();
		Object retVal = new TestClass().get();
		Map<String, String> result = LocalThreadManager.get().getThreads();
		assertEquals(1, result.size());
		assertEquals("Hello",retVal.toString());
	}

	@Test
	public void testSet() {
		LocalThreadManager.get().reset();
		new TestClass().set(101);
		Map<String, String> result = LocalThreadManager.get().getThreads();
		assertEquals(1, result.size());
	}

	@Test
	public void testError() {
		LocalThreadManager.get().reset();
		try {
			new TestClass().error();
		} catch (Exception e) {
			Map<String, String> result = LocalThreadManager.get().getThreads();
			assertEquals(1, result.size());
			assertEquals("TestObject Error", e.getMessage());
		}
	}

	@Test
	public void testParam() {
		LocalThreadManager.get().reset();
		TestObject retObj = new TestClass().param(new TestObject(), true);
		Map<String, String> result = LocalThreadManager.get().getThreads();
		assertEquals(1, result.size());
		assertEquals(true, retObj instanceof TestObject);
		assertEquals(100, ((TestObject)retObj).getId());
	}

}
