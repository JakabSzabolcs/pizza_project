package org.example.service.impl;

import org.example.dao.CoreDAO;
import org.example.dao.UserDAO;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserServiceImpl extends AbstractServiceImpl<User> implements UserService {

    @Inject
    private UserDAO userDao;
    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);

    }
    public static void main(String[] args) {
        System.out.println(new UserServiceImpl().hashPassword("user"));
    }
    private  String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

}
