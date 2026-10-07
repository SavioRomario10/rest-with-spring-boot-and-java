package io.savioromario10.rest_spring_java.service;

import io.savioromario10.rest_spring_java.controller.PersonController;
import io.savioromario10.rest_spring_java.data.dto.v1.PersonDTO;
import io.savioromario10.rest_spring_java.data.dto.v2.PersonDTOV2;
import io.savioromario10.rest_spring_java.exception.RequiredObjectsNullException;
import io.savioromario10.rest_spring_java.exception.ResourceNotFoundException;
import io.savioromario10.rest_spring_java.mapper.custom.PersonMapper;
import io.savioromario10.rest_spring_java.model.Person;
import io.savioromario10.rest_spring_java.repository.PersonRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;

import static io.savioromario10.rest_spring_java.mapper.ObjectMapper.parceListObject;
import static io.savioromario10.rest_spring_java.mapper.ObjectMapper.parceObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper converter;

    private Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    public List<PersonDTO> findAll(){
        logger.info("finding all people");

        var persons = parceListObject(repository.findAll(), PersonDTO.class);

        persons.forEach(this::addHateoasLink);

        return persons;
    }

    public PersonDTO findById(long id) {
        logger.info("find one person");

        var entity = repository.findById(id).orElseThrow(() ->
        {
            logger.warn("No records found for this ID");

            return new ResourceNotFoundException("No records found for this ID");
        });

        var dto = parceObject(entity, PersonDTO.class);

        addHateoasLink(dto);

        return dto;
    }

    public PersonDTO create(PersonDTO person){

        if(person == null) throw new RequiredObjectsNullException();

        logger.info("create one person");

        var entity = parceObject(person, Person.class);

        var dto = parceObject(repository.save(entity), PersonDTO.class);

        addHateoasLink(dto);

        return dto;
    }

    public PersonDTOV2 createV2(PersonDTOV2 person){
        logger.info("create one person V2");

        var entity = converter.convertDTOtoEntity(person);

        return converter.convertEntityToDTO(repository.save(entity));
    }

    public PersonDTO update(PersonDTO person){

        if(person == null) throw new RequiredObjectsNullException();

        logger.info("update one person");

        Person entity = repository.findById(person.getId()).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var dto = parceObject(repository.save(entity), PersonDTO.class);

        addHateoasLink(dto);

        return dto;
    }

    public void delete(Long id){
        logger.info("delete one person");

        Person entity = repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("No records found for this ID"));

        repository.delete(entity);
    }

    @Transactional
    public PersonDTO disablePerson(Long id){
        logger.info("disable one person");

        repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("No records found for this ID"));

        repository.disablePerson(id);

        var entity = repository.findById(id).get();
        var dto = parceObject(entity, PersonDTO.class);
        addHateoasLink(dto);

        return dto;
    }

    private void addHateoasLink(PersonDTO dto) {

        dto.add(linkTo(methodOn(PersonController.class)
                .findById(dto.getId())).withSelfRel().withType("GET"));

        dto.add(linkTo(methodOn(PersonController.class)
                .findAll()).withRel("findAll").withType("GET"));

        dto.add(linkTo(methodOn(PersonController.class)
                .create(dto)).withRel("create").withType("POST"));

        dto.add(linkTo(methodOn(PersonController.class)
                .update(dto)).withRel("update").withType("PUT"));

        dto.add(linkTo(methodOn(PersonController.class)
                .delete(dto.getId())).withRel("delete").withType("DELETE"));

        dto.add(linkTo(methodOn(PersonController.class)
                .disablePerson(dto.getId())).withRel("disable").withType("PATCH"));
    }
}