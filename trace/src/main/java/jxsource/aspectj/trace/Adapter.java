package jxsource.aspectj.trace;

/*
 * Convert Object to String array which will output in sequence to trace log
 * 
 * Implementing class must have no args constructor
 */
public interface Adapter {
	public Object convert(Object obj);
}
