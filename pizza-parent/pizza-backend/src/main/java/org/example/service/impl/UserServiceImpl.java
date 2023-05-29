package org.example.service.impl;

import org.example.dao.UserDAO;
import org.example.entity.User;
import org.example.service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserServiceImpl extends CoreServiceImpl<User> implements UserService {


    @Inject
    private UserDAO dao;


    @Override
    public User findByUsername(String username) {
        return  dao.findByUsername(username);
    }

    @Override
    public boolean checkUsernameExists(String username) {
        return dao.findByUsername(username) != null;
    }



//    public static void main(String[] args) {
//        System.out.println(new UserServiceImpl().hashPassword("user"));
//    }
//    private  String hashPassword(String password) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        return passwordEncoder.encode(password);
//    }

}
