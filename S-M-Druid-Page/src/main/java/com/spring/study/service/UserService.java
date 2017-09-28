package com.spring.study.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.study.entity.User;
import com.spring.study.mapper.UserMapper;

/**
 * Created by zl on 2015/8/27.
 */

@Service
@Transactional(readOnly=true,rollbackFor=Exception.class)
public class UserService {
			private Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;
    
    public String getUserInfo(Integer pageNum,Integer pageSize){
    	logger.info("进入service,开始调用userMapper====");
    	logger.info("开始判断分页参数");
    	pageNum = pageNum == null ? 1:pageNum;
    	pageSize = pageSize == null ? 1:pageSize;
    	PageHelper.startPage(pageNum, pageSize);//启动分页设置
    	logger.info("页数：" + pageNum + "每页条数：" + pageSize);
    	logger.info("开始查询==================");
        List<User> list = userMapper.selectUser(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(list);
        Page page = (Page) list;
        logger.info("userMapper调用结束，返回user对象" + list.toString());
        return  "pageInfo:" + JSON.toJSONString(pageInfo) +"page:" + JSON.toJSONString(page);
    }
    
  //注意：方法的@Transactional会覆盖类上面声明的事务  
    //Propagation.REQUIRED ：有事务就处于当前事务中，没事务就创建一个事务  
    //isolation=Isolation.DEFAULT：事务数据库的默认隔离级别  
    @Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false) 
    public int addUser(User user){
    	int i = userMapper.insertUser(user);
    	return i ;
    }
    
    public PageInfo<User> queryPage(String userName,int pageNum,int pageSize){  
        Page<User> page = PageHelper.startPage(pageNum, pageSize);  
        //PageHelper会自动拦截到下面这查询sql  
        this.userMapper.selectUser(pageNum,pageSize); 
        return page.toPageInfo();  
    }
    
  //测试事务  
    //注意：方法的@Transactional会覆盖类上面声明的事务  
    //Propagation.REQUIRED ：有事务就处于当前事务中，没事务就创建一个事务  
    //isolation=Isolation.DEFAULT：事务数据库的默认隔离级别  
    @Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false)  
    public void testTransational(int id){  
          
        //删除用户
        this.userMapper.deleteUser(id);
        //新增  
        User u = new User();  
        u.setId("123");
        u.setAge(18); 
        u.setName("dxk");
        u.setPassword("123456");
        this.userMapper.insertUser(u);  
        //制造异常  
        //如果类上面没有@Transactional,方法上也没有，哪怕throw new RuntimeException,数据库也会提交  
        throw new RuntimeException("事务异常测试");  
    } 
    
    
    
    

}
