package jxsource.aspectj.springboot.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jxsource.aspectj.springboot.app.bean.Converter;
import jxsource.aspectj.springboot.app.bean.TestCode;

@SpringBootApplication
public class AspectjSpringBootApp implements CommandLineRunner {
	private static Logger log = LogManager.getLogger(AspectjSpringBootApp.class);

	@Autowired
	TestCode testCode;
	@Autowired
	Converter converter;
	
	public static void main(String[] args) {
		SpringApplication.run(AspectjSpringBootApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.debug("start ....");
		testCode.get();
		converter.convert(100);
	}
}
