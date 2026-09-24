package io.savioromario10.rest_spring_java.controller;

import io.savioromario10.rest_spring_java.model.Person;
import io.savioromario10.rest_spring_java.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAll(){
        return service.findnAll();
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findById(@PathVariable("id") String id){
        return service.findById(id);
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person create(@RequestBody Person person){
        return service.create(person);
    }

    @PutMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person update(@PathVariable("id") String id, @RequestBody Person person){
        return service.update(id, person);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") String id){
        service.delete(id);
    }
}