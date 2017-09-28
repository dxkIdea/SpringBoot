package com.spring.study.mapper;

import java.util.List;

import com.spring.study.entity.User;

/**
 * 
 * @author dxk
 *
 */
public interface UserMapper {
	//添加用户
	int insertUser(User user);
	//查询用户
    List<User> selectUser(Integer pageNum,Integer pageSize);
	//删除用户
    void deleteUser(Integer id);
    
}
