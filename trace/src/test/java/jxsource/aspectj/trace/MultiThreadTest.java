package jxsource.aspectj.trace;

public class MultiThreadTest extends Thread
{
	private String str;
	public MultiThreadTest(String str) {
		this.str = str;
	}
	public void run() {
		System.out.println("Hello: "+str);		
	}
	public static void main(String[] args)
	{
		Thread t1 = new MultiThreadTest("A");
		t1.start();
		Thread t2 = new MultiThreadTest("B");
		t2.start();
		try {
			t1.join(1000);
			t1.join(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

