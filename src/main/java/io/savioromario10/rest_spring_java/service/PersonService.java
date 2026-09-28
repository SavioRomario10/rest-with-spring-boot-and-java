package io.savioromario10.rest_spring_java.service;

import io.savioromario10.rest_spring_java.exception.ResourceNotFoundException;
import io.savioromario10.rest_spring_java.model.Person;
import io.savioromario10.rest_spring_java.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    public List<Person> findnAll(){
        logger.info("findibg all people");

        return repository.findAll();
    }

    public Person findById(long id) {
        logger.info("find one person");

        return repository.findById(id).orElseThrow(() ->
        {
            logger.warn("No records found for this ID");

            return new ResourceNotFoundException("No records found for this ID");
        });
    }

    public Person create(Person person){
        logger.info("create one person");

        return repository.save(person);
    }

    public Person update(Person person){
        logger.info("update one person");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(entity);
    }

    public void delete(Long id){
        logger.info("delete one person");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        repository.delete(entity);
    }
}