package jxsource.aspectj.test.springboot.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jxsource.aspectj.testcode.converter.Converter;
import jxsource.aspectj.testcode.main.TestCode;

@RestController
public class AspectjSpringBootController {
	@GetMapping("/")
	public String get() {
		return TestCode.get();
	}
	@RequestMapping(value="/{value}", method=RequestMethod.GET)
	public String integer(@PathVariable("value") int value) {
		return Converter.convert(value);
	}


}
