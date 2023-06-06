package org.maximsavin.api_bookstore.domain.genre;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public interface GenreService {

    List<Genre> getAll();

    Genre getById(long id) throws EntityNotFoundException;

    Genre create(Genre genre) throws DataIntegrityViolationException;

    Genre update(Genre genre);

    void deleteById(long id) throws EntityNotFoundException;
}
