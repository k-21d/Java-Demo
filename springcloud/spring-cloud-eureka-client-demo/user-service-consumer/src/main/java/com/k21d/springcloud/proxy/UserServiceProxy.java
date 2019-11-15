package com.k21d.springcloud.proxy;

import com.k21d.springcloud.domain.User;
import com.k21d.springcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/**
 *
 */
@Service
public class UserServiceProxy implements UserService {

    private static final String PROVIDER_SERVER_URL_PREFIX = "http://user-service-provider";

    /**
     * 通过REST API代理到服务器提供者
     *
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * @param user
     * @return 如果保存成功的话，返回{@link User} ,否则返回<code>null</code>
     */
    @Override
    public boolean save(User user) {
        User returnValue = restTemplate.postForObject(PROVIDER_SERVER_URL_PREFIX + "/user/save", user, User.class);
        return returnValue != null;
    }

    @Override
    public Collection<User> findAll() {
        return restTemplate.getForObject(PROVIDER_SERVER_URL_PREFIX+"/user/findAll",Collection.class);
    }
}
