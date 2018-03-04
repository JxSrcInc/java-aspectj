package jxsource.aspectj.trace;

public class TestObject {
	public String get() {
		return "Hello";
	}
	public void error() {
		throw new RuntimeException("TestObject Error");
	}
}
