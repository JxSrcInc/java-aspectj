package jxsource.aspectj.springboot.app.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class TestCode {
	private static Logger log = LogManager.getLogger(TestCode.class);

	public String get() {
		log.debug("get() ....");
		return "test code";
	}
	public void set(String s) {
		log.debug("set(): "+s);
	}
	public void error() {
		throw new RuntimeException("TestObject Error");
	}
//	public static void main(String...args) {
//		TestCode.get();
//		TestCode.set("ABC");
//		Converter.convert(2345);
//		try {
//			TestCode.error();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
}
