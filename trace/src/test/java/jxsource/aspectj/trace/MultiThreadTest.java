package jxsource.aspectj.trace;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

public class MultiThreadTest {
	@Test
	public void test() {
		try {
			Thread t1 = new Thread(new TestRunnable("A"),"Thread-A");
			t1.start();
			t1.join();
			Thread t2 = new Thread(new TestRunnable("B"),"Thread-B");
			t2.start();
			t2.join();
			Map<String,String> results = ThreadManager.getInstance().getThreads();
			for(Map.Entry<String,String> entry: results.entrySet()) {
				String thread = entry.getKey();
				int i = thread.indexOf('-');
				assertEquals(thread.substring(i+1), entry.getValue());
			}
		} catch (InterruptedException e) {
			assertTrue(false);
		}

	}
}
