package jxsource.aspectj.trace;

public class AspectjThreadLocal
{
  public static final ThreadLocal<ThreadInfo> aspectThreadLocal = new ThreadLocal<ThreadInfo>();

  public static void set(ThreadInfo info) {
    aspectThreadLocal.set(info);
  }

  public static void unset() {
    aspectThreadLocal.remove();
  }

  public static ThreadInfo get() {
    return (ThreadInfo) aspectThreadLocal.get();
  }

}
