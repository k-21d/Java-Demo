package com.k21d.spring.springrest.controller;

import com.k21d.spring.springrest.domain.Person;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonRestController {

    @GetMapping("/person/{id}")
    public Person person(@PathVariable Long id, @RequestParam(required = false)String name){
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        return person;
    }
    @PostMapping(value = "/person/json/to/properties",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = "applicaiton/properties+person"  // 响应类型
    )
    public Person personJsonToProperties(@RequestBody Person person){
        //@RequestBody的内容是JSON
        //响应的内容是Properties
        return person;
    }

    @PostMapping(value = "/person/properties/to/json",
        consumes = "applicaiton/properties+person",
        produces =  MediaType.APPLICATION_JSON_VALUE  // 响应类型
    )
    public Person personPropertiesToJson(@RequestBody Person person) {
        // @RequestBody 的内容是 Properties
        // 响应的内容是 JSON
        return person;
    }
}
