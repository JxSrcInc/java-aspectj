package jxsource.aspectj.trace;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadManager {
	private Map<String, String> threads = new ConcurrentHashMap<String, String>();
	private static ThreadManager mgr;
	private ThreadManager() {
		
	}
	public static ThreadManager getInstance() {
		if(mgr == null) {
			mgr = new ThreadManager();
		}
		return mgr;
	}
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
