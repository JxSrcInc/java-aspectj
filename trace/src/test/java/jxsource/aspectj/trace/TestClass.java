package jxsource.aspectj.trace;

public class TestClass {
	public String get() {
		return "Hello";
	}
	public void error() {
		throw new RuntimeException("TestObject Error");
	}
	public TestObject param(TestObject obj, boolean b) {
		obj.setId(100);
		obj.getMap().put("string","String Message");
		return obj;
		
	}
}
