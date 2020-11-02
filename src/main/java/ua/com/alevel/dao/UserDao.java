package ua.com.alevel.dao;

import ua.com.alevel.entity.User;

import java.util.Optional;

/**
 * @author Iehor Funtusov, created 02/11/2020 - 9:29 PM
 */

public interface UserDao extends CorOneDao<User, Integer> {

    Optional<User> findByEmailAndName(String email);
}
