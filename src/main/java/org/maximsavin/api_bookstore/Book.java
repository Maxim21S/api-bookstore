package org.maximsavin.api_bookstore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "books")
@Data
@EqualsAndHashCode(of = "isbn")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "isbn", nullable = false, unique = true, length = 20)
    private String isbn;

    @Column(name = "publication_year")
    private LocalDate publicationYear;

    @Version
    @Column(name = "version")
    private Timestamp version;
}
