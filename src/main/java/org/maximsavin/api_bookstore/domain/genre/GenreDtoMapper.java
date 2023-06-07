package org.maximsavin.api_bookstore.domain.genre;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public interface GenreDtoMapper {

    List<GenreDto> getAll();

    GenreDto getById(long id) throws EntityNotFoundException;

    GenreDto create(GenreRequest request) throws DataIntegrityViolationException;

    GenreDto update(long id, GenreRequest request) throws EntityNotFoundException, DataIntegrityViolationException;

    void deleteById(long id) throws EntityNotFoundException;
}
