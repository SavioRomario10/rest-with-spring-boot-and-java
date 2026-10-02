package io.savioromario10.rest_spring_java.data.dto.v1;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.Objects;

public class BookDTO extends RepresentationModel<BookDTO> {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String author;
    private String title;
    private Double price;
    private LocalDateTime launchDate;

    public BookDTO() {}

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getLaunchDate() {
        return launchDate;
    }
    public void setLaunchDate(LocalDateTime launchDate) {
        this.launchDate = launchDate;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BookDTO book)) return false;
        return Objects.equals(getId(), book.getId()) && Objects.equals(getAuthor(), book.getAuthor()) &&
                Objects.equals(getTitle(), book.getTitle()) &&
                Objects.equals(getPrice(), book.getPrice()) &&
                Objects.equals(getLaunchDate(), book.getLaunchDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthor(), getTitle(), getPrice(), getLaunchDate());
    }
}