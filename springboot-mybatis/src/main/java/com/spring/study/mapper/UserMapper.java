package com.spring.study.mapper;

import com.spring.study.entity.User;

/**
 * Created by zl on 2015/8/27.
 */
public interface UserMapper {
    User findUserInfo();
    int insertUser(User user);
}
