package com.springboot.Controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Application;

@RestController
public class HelloController {
	private static Logger logger = Logger.getLogger(Application.class);
	
	@RequestMapping("/hello")
	public String hello(){
		logger.info(logger.getClass()+"SpringBoot---成功访问‘hello’");
		return "hello";
	}
	
}
