package jxsource.aspectj.testcode.converter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Converter {
	private static Logger log = LogManager.getLogger(Converter.class);
	public static String convert(int i) {
		log.error("convert(): "+i);
		return "Integer "+Integer.toString(i);
	}

}
