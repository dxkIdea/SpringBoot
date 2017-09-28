package com.spring.study.controller;


import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.spring.study.entity.User;
import com.spring.study.service.UserService;

/**
 * 
 * @author dxk
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    
    //测试插入
    @RequestMapping(value="/addUser"/*,method = RequestMethod.POST*/)
    @ResponseBody
    public int addUser(){
    	//用户ID为主键，自增，无需set
    	User user = new User();
    	user.setAge(18);
    	user.setName("dxk");
    	user.setPassword("123456");
    	return userService.addUser(user);
    }
    
    //测试查询
    @RequestMapping(value="/getUserInfo"/*,method = RequestMethod.POST*/)
    @ResponseBody
    public String getUserInfo(@RequestParam("pageNum") int pageNum,
    						@RequestParam("pageSize") int pageSize){
    	logger.info("getUserInfo 方法调用开始====，进入service调用====");
        String infoStr  = userService.getUserInfo(pageNum,pageSize);
        if(infoStr!=null){
            logger.info("service调用结束，返回对象"+infoStr);
        }
        return infoStr;
    }
   
    
    /** 
     * 测试分页插件 
     * @return 
     */  
    @RequestMapping(value="/queryPage",method = RequestMethod.POST)  
    @ResponseBody  
    public String queryPage(){  
        PageInfo<User> page = this.userService.queryPage("tes", 1, 2);  
        System.out.println("总页数=" + page.getPages());  
        System.out.println("总记录数=" + page.getTotal()) ;  
        for(User u : page.getList()){  
            System.out.println(u.getId() + " \t " + u.getName());  
        }  
        return "success";  
    }  
    /** 
     * 测试事务 
     * @return 
     */  
    @RequestMapping(value="/testTransational"/*,method = RequestMethod.POST*/)  
    @ResponseBody  
    public String test(@RequestParam("id") int id){  
        try {  
            this.userService.testTransational(id);//测试事务  删除一条数据  
            return "success";  
        } catch (Exception e) {  
            return e.getMessage();  
        }  
          
    }
    
    @RequestMapping(value="registUser",method=RequestMethod.POST)
    public String regUser(@RequestParam("Params") String  Params){
    	System.out.println(Params.toString());
    	return Params.toString();
    }
    
}
