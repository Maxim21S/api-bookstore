package org.maximsavin.api_bookstore.domain.genre;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface GenreService {

    List<Genre> getAll();

    Genre getById(long id);

    Genre create(Genre genre);

    Genre update(Genre genre);

    void deleteById(long id) throws EntityNotFoundException;
}
