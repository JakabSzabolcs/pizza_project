package org.example.service;

import org.example.entity.User;

public interface UserService extends CoreService<User>
{
    User findByUsername(String username);
}
