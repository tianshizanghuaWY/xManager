package com.qianyang.service.impl;

import com.qianyang.dao.UserDao;
import com.qianyang.model.User;
import com.qianyang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    public int save(User user) {
        return userDao.save(user);
    }
}
