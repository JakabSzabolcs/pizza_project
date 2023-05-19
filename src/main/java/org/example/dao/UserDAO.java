package org.example.dao;

import org.example.entity.User;

public interface UserDAO extends CoreDAO<User>
{
    User findByUsername(String username);
}
