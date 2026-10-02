package io.savioromario10.rest_spring_java.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="books")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "author", nullable = false, length = 80)
    private String author;
    @Column(name = "title", nullable = false, length = 100)
    private String title;
    @Column(name = "price", nullable = false, precision = 65, scale = 2)
    private Double price;
    @Column(name = "launch_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDateTime launchDate;

    public Book() {}

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
        if (!(o instanceof Book book)) return false;
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