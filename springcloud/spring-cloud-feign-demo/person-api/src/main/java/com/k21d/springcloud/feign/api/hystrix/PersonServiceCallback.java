package com.k21d.springcloud.feign.api.hystrix;

import com.k21d.springcloud.feign.api.domain.Person;
import com.k21d.springcloud.feign.api.service.PersonService;

import java.util.Collection;
import java.util.Collections;

/**
 * {@link PersonService} fallback实现
 */
public class PersonServiceCallback implements PersonService {
    @Override
    public boolean save(Person person) {
        return false;
    }

    @Override
    public Collection<Person> findAll() {
        return Collections.emptyList();
    }
}
