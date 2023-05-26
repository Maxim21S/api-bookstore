package org.maximsavin.api_bookstore.domain.genre;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface GenreDtoMapperService {

    List<GenreDtoResponse> getAll();

    GenreDtoResponse getById(long id);

    GenreDtoResponse create(GenreDtoRequest request);

    GenreDtoResponse update(long id, GenreDtoRequest request);

    void deleteById(long id) throws EntityNotFoundException;
}
