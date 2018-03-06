package jxsource.aspectj.trace;

/*
 * Report stacks on the current thread
 * It is used if you want to see all calls on thread from Thread.run() method
 */
public class ThreadStack {
	
	// stacks with index less than currentIndex are Trace package calls
	private int currentIndex = 3;
	private StackTraceElement[] elements = Thread.currentThread().getStackTrace();
	
	public ThreadStack()
	{
		this(true); 
	}
	
	public ThreadStack(boolean ajc)
	{
		if(ajc)
			currentIndex = 6;
	}
	public int size()
	{
		return elements.length;
	}

	public StackTraceElement getStackTraceElement(int index)
	{
		if(index < elements.length)
			return elements[index];
		else
			return null;
	}
	
	public String getMethod(int index)
	{
		StackTraceElement element = getStackTraceElement(index);
		return (element==null?null:element.getClassName()+"."+element.getMethodName());
	}
	
	public String toString()
	{
		String s = "";
		for(int i=currentIndex; i<Math.min(Constants.displayStackLength+currentIndex,elements.length); i++)
		{
			s += elements[i].toString() + "\n";
		}
		return s;
	}
	
//	public String toXML()
//	{
//		String s = "";
//		for(int i=currentIndex; i<Math.min(Constants.displayStackLength+currentIndex,elements.length); i++)
//		{
//			s += "<![CDATA["+elements[i].toString() + "]]>\n";
//		}
//		return s;
//	}
	public String toXML()
	{
		String s = "<![CDATA[\n";
		for(int i=currentIndex; i<Math.min(Constants.displayStackLength+currentIndex,elements.length); i++)
		{
			s += elements[i].toString()+'\n';
		}
		return s + "]]>\n";
	}

	public StackTraceElement getCurrentElement()
	{
		return getStackTraceElement(currentIndex);
	}
	
	public String getCurrentMethod()
	{
		return getMethod(currentIndex);
	}
	
	public String getCurrentCaller()
	{
		return getMethod(currentIndex+1);
	}
	
}
