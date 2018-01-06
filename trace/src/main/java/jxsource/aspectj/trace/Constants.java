package jxsource.aspectj.trace;

public class Constants {
	public static String traceDir = "c:\\temp\\aspectj\\trace";
	public static String root = "aspectjTrace";
	public static int displayStackLength = 10;
	static {
		String dir = System.getProperty("jxsource.aspectj.traceDir");
		if(dir != null)
			traceDir = dir;
	}
}
