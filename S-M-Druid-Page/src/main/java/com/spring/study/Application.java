package com.spring.study;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@EnableAutoConfiguration
//@ComponentScan(basePackages={"com.spring.study"})
//@MapperScan(basePackages={"com.spring.study.mapper"})
@ServletComponentScan //配置druid必须加的注解，如果不加，访问页面打不开，filter和servlet、listener之类的需要单独进行注册才能使用，spring boot里面提供了该注解起到注册作用
@SpringBootApplication
public class Application {
	 private static Logger logger = Logger.getLogger(Application.class);
	
    //Main Start
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(Application.class, args);
		logger.info("==============SpringBoot Start Success==============");

	}

}
