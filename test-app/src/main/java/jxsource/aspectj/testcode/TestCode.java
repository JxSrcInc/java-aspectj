package jxsource.aspectj.testcode;

public class TestCode {

	public static String get() {
		return "test code";
	}
	public static String convert(int i) {
		return "Integer "+Integer.toString(i);
	}
	public static void main(String...args) {
		TestCode.get();
		TestCode.convert(2345);
		System.out.println("Complete");
	}
}
