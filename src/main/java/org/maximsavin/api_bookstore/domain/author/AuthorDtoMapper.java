package org.maximsavin.api_bookstore.domain.author;

import java.util.List;

public interface AuthorDtoMapper {

    List<AuthorDto> getAll();

    AuthorDto getById(long id);

    AuthorDto create(AuthorRequest request);

    AuthorDto update(long id, AuthorRequest request);

    void deleteById(long id);
}
