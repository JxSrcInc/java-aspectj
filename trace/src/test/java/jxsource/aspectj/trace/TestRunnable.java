package jxsource.aspectj.trace;

public class TestRunnable implements Runnable {

	private String msg;
	public TestRunnable(String msg) {
		this.msg = msg;
	}
    public void run() {
        ThreadManager.getInstance().add(Thread.currentThread().getName(), msg);
    }
}
