package io.savioromario10.rest_spring_java.service;

import io.savioromario10.rest_spring_java.controller.BookController;
import io.savioromario10.rest_spring_java.data.dto.v1.BookDTO;
import io.savioromario10.rest_spring_java.exception.RequiredObjectsNullException;
import io.savioromario10.rest_spring_java.exception.ResourceNotFoundException;
import io.savioromario10.rest_spring_java.model.Book;
import io.savioromario10.rest_spring_java.repository.BookRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import static io.savioromario10.rest_spring_java.mapper.ObjectMapper.parceObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {
    @Autowired
    BookRepository repository;

    @Autowired
    PagedResourcesAssembler<BookDTO> assembler;

    private Logger logger = LoggerFactory.getLogger(BookService.class.getName());

    public PagedModel<EntityModel<BookDTO>> findAll(Pageable pageable){
        logger.info("finding all people");

        var books = repository.findAll(pageable);

        var booksWithLinks = books.map(book -> {
            BookDTO dto = parceObject(book, BookDTO.class);
            addHateoasLink(dto);
            return dto;
        });

        Link findAllLink = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(BookController.class)
                        .findAll(pageable.getPageNumber(), pageable.getPageSize(), String.valueOf(pageable.getSort()))
                ).withSelfRel().withType("GET");

        return assembler.toModel(booksWithLinks, findAllLink);
    }

    public BookDTO findById(long id) {
        logger.info("find one Book");

        var entity = repository.findById(id).orElseThrow(() ->
        {
            logger.warn("No records found for this ID");

            return new ResourceNotFoundException("No records found for this ID");
        });

        var dto = parceObject(entity, BookDTO.class);

        addHateoasLink(dto);

        return dto;
    }

    public BookDTO create(BookDTO Book){

        if(Book == null) throw new RequiredObjectsNullException();

        logger.info("create one Book");

        var entity = parceObject(Book, Book.class);

        var dto = parceObject(repository.save(entity), BookDTO.class);

        addHateoasLink(dto);

        return dto;
    }

    public BookDTO update(BookDTO Book){

        if(Book == null) throw new RequiredObjectsNullException();

        logger.info("update one Book");

        Book entity = repository.findById(Book.getId()).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID"));

        entity.setAuthor(Book.getAuthor());
        entity.setLaunchDate(Book.getLaunchDate());
        entity.setPrice(Book.getPrice());
        entity.setTitle(Book.getTitle());

        var dto = parceObject(repository.save(entity), BookDTO.class);

        addHateoasLink(dto);

        return dto;
    }

    public void delete(Long id){
        logger.info("delete one Book");

        Book entity = repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("No records found for this ID"));

        repository.delete(entity);
    }

    private void addHateoasLink(BookDTO dto) {

        dto.add(linkTo(methodOn(BookController.class)
                .findById(dto.getId())).withSelfRel().withType("GET"));

        dto.add(linkTo(methodOn(BookController.class)
                .findAll(1, 12, "asc")).withRel("findAll").withType("GET"));

        dto.add(linkTo(methodOn(BookController.class)
                .create(dto)).withRel("create").withType("POST"));

        dto.add(linkTo(methodOn(BookController.class)
                .update(dto)).withRel("update").withType("PUT"));

        dto.add(linkTo(methodOn(BookController.class)
                .delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}