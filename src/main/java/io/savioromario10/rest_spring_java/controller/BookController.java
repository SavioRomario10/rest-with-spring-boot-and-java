package io.savioromario10.rest_spring_java.controller;

import io.savioromario10.rest_spring_java.controller.docs.BookControllerDocs;
import io.savioromario10.rest_spring_java.data.dto.v1.BookDTO;
import io.savioromario10.rest_spring_java.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController implements BookControllerDocs {
    @Autowired
    private BookService service;

    @Override
    @GetMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE})
    public ResponseEntity<List<BookDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    @GetMapping(
            value = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE})
    public ResponseEntity<BookDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @Override
    @PostMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE},
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE})
    public ResponseEntity<BookDTO> create(@RequestBody BookDTO BookDTO){
        return ResponseEntity.status(201).body(service.create(BookDTO));
    }

    @Override
    @PutMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE},
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE})
    public ResponseEntity<BookDTO> update(@RequestBody BookDTO BookDTO){
        return ResponseEntity.ok(service.update(BookDTO));
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
