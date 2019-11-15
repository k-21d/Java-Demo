package com.k21d.springcloud.service;

import com.k21d.springcloud.domain.User;
import com.k21d.springcloud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * {@link UserService 用户服务} 提供者实现
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    private ConcurrentMap<Long,User> repository = new ConcurrentHashMap<>();

    private static final AtomicLong idGenerator = new AtomicLong(0);

    @Override
    public boolean save(User user) {
        long id = idGenerator.incrementAndGet();
        user.setId(id);
        return repository.putIfAbsent(id,user) == null;
    }

    @Override
    public Collection<User> findAll() {
        return repository.values();
    }
}
