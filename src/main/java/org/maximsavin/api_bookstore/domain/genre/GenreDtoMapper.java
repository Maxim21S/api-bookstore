package org.maximsavin.api_bookstore.domain.genre;

import java.util.List;

public interface GenreDtoMapper {

    List<GenreDtoResponse> getAll();

    GenreDtoResponse getById(long id);

    GenreDtoResponse create(GenreDtoRequest request);

    GenreDtoResponse update(long id, GenreDtoRequest request);

    void deleteById(long id);
}
