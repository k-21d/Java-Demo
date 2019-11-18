package com.k21d.springcloud.feign.person.service.provider.web.controller;

import com.k21d.springcloud.feign.api.domain.Person;
import com.k21d.springcloud.feign.api.service.PersonService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@link PersonService} 提供者控制器,可选实现{@link PersonService}接口
 */
@RestController
public class PersonServiceProvicerController {
    private Map<Long, Person> persons = new ConcurrentHashMap<>();
    private final static Random random = new Random();

    @PostMapping(value = "/person/save")
    public boolean save(@RequestBody Person person) {
        return persons.put(person.getId(),person)==null;
    }
    @GetMapping(value = "/person/findAll")
    @HystrixCommand(fallbackMethod = "fallbackForPersons",
        commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",
            value = "100")
        })
    public Collection<Person> findAll() throws InterruptedException {
        int value = random.nextInt(200);
        System.out.println("time:"+value);
        Thread.sleep(value);
        return persons.values();
    }

    /**
     * {@link #findAll()}Fallback方法
     * @return 返回空集合
     */
    public Collection<Person> fallbackForPersons(){
        System.err.println("fallbackForPersons() is inveked~ ");
        return Collections.emptyList();
    }
}
