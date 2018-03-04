package jxsource.aspectj.testcode.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jxsource.aspectj.testcode.converter.Converter;

public class TestCode {
	private static Logger log = LogManager.getLogger(TestCode.class);

	public static String get() {
		log.debug("get() ....");
		return "test code";
	}
	public static void set(String s) {
		log.debug("set(): "+s);
	}
	public static void main(String...args) {
		TestCode.get();
		TestCode.set("ABC");
		Converter.convert(2345);
	}
}
