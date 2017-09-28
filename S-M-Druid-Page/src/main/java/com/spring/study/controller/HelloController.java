package com.spring.study.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
	private Logger logger = Logger.getLogger(HelloController.class);
	
	@RequestMapping("/")
	public String index (){
		logger.info("====进入index页面====");
		return "index";
	}
    @RequestMapping("/hello")
    public String greeting(@RequestParam(value="name", required=false, 
    						defaultValue="World") String name, Model model) {
    	
        model.addAttribute("name", name);
        logger.info("====进入hello页面=======");
        return "hello";
    }
    
}
