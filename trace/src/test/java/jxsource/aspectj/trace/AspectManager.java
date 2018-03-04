package jxsource.aspectj.trace;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AspectManager {
	// key: ThreadName-Signature
	private Map<String, String> threads = new ConcurrentHashMap<String, String>();
	public void add(String thread, String msg) {
		threads.put(thread, msg);
	}
	public Map<String, String> getThreads() {
		return threads;
	}
	public void reset() {
		threads.clear();
	}
}
