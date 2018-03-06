package jxsource.aspectj.testcode.main;

import jxsource.aspectj.testcode.converter.Converter;

public class MainTest {
	public static void main(String...args) {
		TestCode.get();
		TestCode.set("ABC");
		Converter.convert(2345);
		try {
			TestCode.error();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
