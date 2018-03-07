package jxsource.aspectj.trace;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

public class ThreadTraceAspectTest {
	@Test
	public void test() {
		LocalThreadManager.get().reset();
		new TestClass().get();
//		System.out.println(LocalThreadManager.get().getThreads());
		Map<String, String> result = LocalThreadManager.get().getThreads();
		assertEquals(2, result.size());
		for (String key : result.keySet()) {
			if (key.contains("-before-")) {
				assert (true);
			} else if (key.contains("-after-")) {
				assert (true);
			} else {
				assert (false);
			}
		}
	}

	@Test
	public void testError() {
		LocalThreadManager.get().reset();
		try {
			new TestClass().error();
		} catch (Exception e) {
//			System.out.println(LocalThreadManager.get().getThreads());
			Map<String, String> result = LocalThreadManager.get().getThreads();
			assertEquals(2, result.size());
			for (String key : result.keySet()) {
				if (key.contains("-before-")) {
					assert (true);
				} else if (key.contains("-throwing-")) {
					assert (true);
				} else {
					assert (false);
				}
			}
		}
	}

	@Test
	public void testParam() {
		LocalThreadManager.get().reset();
		TestObject retObj = new TestClass().param(new TestObject(), true);
//		System.out.println(LocalThreadManager.get().getThreads());
		Map<String, String> result = LocalThreadManager.get().getThreads();
		assertEquals(2, result.size());
		for (String key : result.keySet()) {
			if (key.contains("-before-")) {
				assert (true);
			} else if (key.contains("-after-")) {
				assert (true);
			} else {
				assert (false);
			}
		}
	}

}
