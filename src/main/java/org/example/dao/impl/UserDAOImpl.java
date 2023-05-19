package org.example.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.example.dao.OrderDAO;
import org.example.dao.UserDAO;
import org.example.entity.Order;
import org.example.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

@Stateless
public class UserDAOImpl extends CoreDAOImpl<User> implements UserDAO {

    @Override
    protected Class<User> getManagedClass() {
        return User.class;
    }

    // az adatbázisba innentől kódolva lesznek a jelszavak
    @Override
    public void add(User user) {
        user.setPassword(hashPassword(user.getPassword()));
        em.persist(user);
    }

    @Override
    public void update(User user) {
        user.setPassword(hashPassword(user.getPassword()));
        em.merge(user);
    }


    private  String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    @Override
    public User findByUsername(String username) {
        try
        {
            return em.createQuery("select n from " + getManagedClass().getSimpleName() + " n where n.username = :username", getManagedClass())
                    .setParameter("username", username).getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }


}
//$2a$12$mOWbXn5TX6Qi.EJjMcp3LeYp87NVRYUaf9wR3cgxYIxVsjxPRhu9G
