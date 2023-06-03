package org.maximsavin.api_bookstore.domain.genre;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface GenreDtoMapper {

    List<GenreDto> getAll();

    GenreDto getById(long id);

    GenreDto create(GenreRequest request);

    GenreDto update(long id, GenreRequest request);

    void deleteById(long id) throws EntityNotFoundException;
}
