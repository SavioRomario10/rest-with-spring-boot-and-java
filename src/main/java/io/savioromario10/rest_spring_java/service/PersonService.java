package io.savioromario10.rest_spring_java.service;

import io.savioromario10.rest_spring_java.data.dto.v1.PersonDTO;
import io.savioromario10.rest_spring_java.data.dto.v2.PersonDTOV2;
import io.savioromario10.rest_spring_java.exception.ResourceNotFoundException;
import io.savioromario10.rest_spring_java.mapper.custom.PersonMapper;
import io.savioromario10.rest_spring_java.model.Person;
import io.savioromario10.rest_spring_java.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static io.savioromario10.rest_spring_java.mapper.ObjectMapper.parceListObject;
import static io.savioromario10.rest_spring_java.mapper.ObjectMapper.parceObject;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper converter;

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    public List<PersonDTO> findAll(){
        logger.info("findibg all people");

        return parceListObject(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(long id) {
        logger.info("find one person");

        var entity = repository.findById(id).orElseThrow(() ->
        {
            logger.warn("No records found for this ID");

            return new ResourceNotFoundException("No records found for this ID");
        });

        return parceObject(entity, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person){
        logger.info("create one person");

        var entity = parceObject(person, Person.class);

        return parceObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTOV2 createV2(PersonDTOV2 person){
        logger.info("create one person V2");

        var entity = converter.convertDTOtoEntity(person);

        return converter.convertEntityToDTO(repository.save(entity));
    }

    public PersonDTO update(PersonDTO person){
        logger.info("update one person");

        Person entity = repository.findById(person.getId()).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parceObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id){
        logger.info("delete one person");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        repository.delete(entity);
    }
}