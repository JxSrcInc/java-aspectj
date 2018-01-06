package jxsource.aspectj.trace;

public class ThreadTraceLocal {
//	public static final ThreadLocal<ThreadTrace> threadTraceLocal = new ThreadLocal<ThreadTrace>();
    private static final ThreadLocal<ThreadTrace> threadTraceLocal =
            new ThreadLocal<ThreadTrace>() {
                @Override protected ThreadTrace initialValue() {
                    return new ThreadTrace();
            }
        };
	public static ThreadTrace set(ThreadTrace trace) {
		threadTraceLocal.set(trace);
		return threadTraceLocal.get();
	}
	// after remove, threadTraceLocal will create a new ThreadTrace object
	public static void reset() {
		threadTraceLocal.remove();
	}
	public static ThreadTrace get() {
		return threadTraceLocal.get();
	}
}
