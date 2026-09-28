package io.savioromario10.rest_spring_java.repository;

import io.savioromario10.rest_spring_java.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}