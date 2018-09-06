package jxsource.aspectj.springboot.app.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Converter {
	private static Logger log = LogManager.getLogger(Converter.class);
	public String convert(int i) {
		log.error("convert(): "+i);
		return "Integer "+Integer.toString(i);
	}

}
