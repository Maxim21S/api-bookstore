package org.maximsavin.api_bookstore.domain.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.maximsavin.api_bookstore.domain.author.AuthorRequest;
import org.maximsavin.api_bookstore.domain.genre.GenreRequest;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO for {@link Book}
 */
public record BookRequest(
        @NotBlank
        @Length(max = 255)
        String title,

        @Length(max = 2000)
        String description,

        @NotNull
        @Length(max = 20)
        @Pattern(regexp = "^[0-9]{10}$|^[0-9]{13}$")
        String isbn,

        @PastOrPresent
        LocalDate publicationYear,

        Set<GenreRequest> genres,

        Set<AuthorRequest> authors

) implements Serializable {
}