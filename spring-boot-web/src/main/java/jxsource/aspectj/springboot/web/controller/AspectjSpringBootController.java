package jxsource.aspectj.springboot.web.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AspectjSpringBootController {
	@GetMapping("/")
	public String get() {
		return getClass().getName();
	}
	@RequestMapping(value="/{value}", method=RequestMethod.GET)
	public String integer(@PathVariable("value") int value) {
		return "The value is "+value;
	}


}
