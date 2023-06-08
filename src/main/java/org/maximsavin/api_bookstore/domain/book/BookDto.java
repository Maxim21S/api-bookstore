package org.maximsavin.api_bookstore.domain.book;

import org.maximsavin.api_bookstore.domain.author.AuthorDto;
import org.maximsavin.api_bookstore.domain.genre.GenreDto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO for {@link Book}
 */
public record BookDto(
        Long id,
        String title,
        String description,
        String isbn,
        LocalDate publicationYear,
        Set<GenreDto> genres,
        Set<AuthorDto> authors
) implements Serializable {
}