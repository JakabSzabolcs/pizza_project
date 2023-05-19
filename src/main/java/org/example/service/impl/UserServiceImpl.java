package org.example.service.impl;

import org.example.dao.CoreDAO;
import org.example.dao.UserDAO;
import org.example.entity.User;
import org.example.service.UserService;

import javax.inject.Inject;

public class UserServiceImpl extends AbstractServiceImpl<User> implements UserService {

    @Inject
    private UserDAO userDao;
    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);

    }
}
