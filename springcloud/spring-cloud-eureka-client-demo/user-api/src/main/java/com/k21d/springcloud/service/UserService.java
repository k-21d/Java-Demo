package com.k21d.springcloud.service;

import com.k21d.springcloud.domain.User;

import java.util.Collection;

public interface UserService {
    boolean save(User user);

    Collection<User> findAll();
}
