package io.savioromario10.rest_spring_java.repository;

import io.savioromario10.rest_spring_java.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
