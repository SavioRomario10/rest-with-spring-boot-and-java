package io.savioromario10.rest_spring_java.controller;

import io.savioromario10.rest_spring_java.model.Person;
import io.savioromario10.rest_spring_java.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Person>> findAll(){
        return ResponseEntity.ok(service.findnAll());
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> create(@RequestBody Person person){
        return ResponseEntity.status(201).body(service.create(person));
    }

    @PutMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> update(@RequestBody Person person){
        return ResponseEntity.ok(service.update(person));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}