package com.spring.springbootWebsocket.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	private static Logger logger = Logger.getLogger(IndexController.class);
	
	@RequestMapping(value = "/ws",method = RequestMethod.GET)
	public String ws(){
		return "ws/ws";
	}
	@RequestMapping("/hello")
	public String hello(){
		logger.info(logger.getClass() + "进入------Hello");
		return "hello";
	}
	
	
	

}
