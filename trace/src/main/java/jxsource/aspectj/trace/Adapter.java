package jxsource.aspectj.trace;

/*
 * Convert Object to String array which will output in sequence to trace log
 */
public interface Adapter {
	public String[] toString(Object obj);
}
