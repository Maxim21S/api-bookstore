package org.maximsavin.api_bookstore.domain.author;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorDtoMapperImpl implements AuthorDtoMapper {

    @Override
    public List<AuthorDto> getAll() {
        return null;
    }

    @Override
    public AuthorDto getById(long id) {
        return null;
    }

    @Override
    public AuthorDto create(AuthorRequest request) {
        return null;
    }

    @Override
    public AuthorDto update(long id, AuthorRequest request) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
