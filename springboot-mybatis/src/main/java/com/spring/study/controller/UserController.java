package com.spring.study.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.study.entity.User;
import com.spring.study.service.UserService;

/**
 * Created by zl on 2015/8/27.
 */
@Controller
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public User getUserInfo() {
        User user = userService.getUserInfo();
        if(user!=null){
            System.out.println("user.getName():"+user.getName());
            logger.info("user.getAge():"+user.getAge());
        }
        return user;
    }
    @RequestMapping("/addUser")
    @ResponseBody
    public int addUser(@RequestParam(value = "id",required=false) int id,
    					@RequestParam(value = "name",required=false) String name,
    					@RequestParam(value = "age",required=false) int age,
    					@RequestParam(value = "password",required=false) String password
    					){
    	User user = new User();
    	user.setId(id);
    	user.setAge(age);
    	user.setName(name);
    	user.setPassword(password);
    	return userService.addUser(user);
    	
    }
    
}
