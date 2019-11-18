package com.k21d.springcloud.feign.person.service.provider.web.controller;

import com.k21d.springcloud.feign.api.domain.Person;
import com.k21d.springcloud.feign.api.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@link PersonService} 提供者控制器,可选实现{@link PersonService}接口
 */
@RestController
public class PersonServiceProvicerController {
    private Map<Long, Person> persons = new ConcurrentHashMap<>();


    @PostMapping(value = "/person/save")
    public boolean save(@RequestBody Person person) {
        return persons.put(person.getId(),person)==null;
    }
    @GetMapping(value = "/person/findAll")
    public Collection<Person> findAll() {
        return persons.values();
    }
}
