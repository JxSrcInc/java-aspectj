package jxsource.aspectj.trace;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

public class ThreadTraceAspectTest {
	@Test
	public void test() {
		LocalThreadManager.get().reset();
		new TestObject().get();
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
			new TestObject().error();
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

}
