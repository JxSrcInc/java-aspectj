package jxsource.aspectj.trace;

public class LocalThreadManager {
    private static final ThreadLocal<AspectManager> ThreadManagerLocal =
            new ThreadLocal<AspectManager>() {
                @Override protected AspectManager initialValue() {
                    return new AspectManager();
            }
        };
	public static AspectManager set(AspectManager trace) {
		ThreadManagerLocal.set(trace);
		return ThreadManagerLocal.get();
	}
	// after remove, ThreadManagerLocal will create a new ThreadManager object
	public static void reset() {
		ThreadManagerLocal.remove();
	}
	public static AspectManager get() {
		return ThreadManagerLocal.get();
	}
}
