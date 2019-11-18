package com.k21d.springcloud.feign.api.service;

import com.k21d.springcloud.feign.api.domain.Person;
import com.k21d.springcloud.feign.api.hystrix.PersonServiceCallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

/**
 * {@link Person} 服务
 */
@FeignClient(value = "person-service", fallback = PersonServiceCallback.class)//服务提供方应用的名称
public interface PersonService {
    /**
     *
     * @param person {@link Person}
     * @return if success <code>true</code>
     */
    @PostMapping(value = "/person/save")
    boolean save(Person person);

    /**
     * find all services
     * @return
     */
    @GetMapping(value = "/person/findAll")
    Collection<Person> findAll();
}
