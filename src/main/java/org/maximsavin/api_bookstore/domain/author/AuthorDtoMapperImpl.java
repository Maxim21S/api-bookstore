package org.maximsavin.api_bookstore.domain.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorDtoMapperImpl implements AuthorDtoMapper {

    private final AuthorServiceImpl service;
    private final AuthorMapper mapper;

    @Override
    public List<AuthorDto> getAll() {
        return service.getAll().stream()
                .map(mapper::authorToAuthorDto)
                .toList();
    }

    @Override
    public AuthorDto getById(long id) {
        return mapper.authorToAuthorDto(service.getById(id));
    }

    @Override
    public AuthorDto create(AuthorRequest request) {
        var author = mapper.authorRequestToAuthor(request);
        author = service.create(author);
        return mapper.authorToAuthorDto(author);
    }

    @Override
    public AuthorDto update(long id, AuthorRequest request) {
        var author = mapper.authorRequestToAuthor(request);
        author.setId(id);
        author = service.update(author);
        return mapper.authorToAuthorDto(author);
    }

    @Override
    public void deleteById(long id) {
        service.deleteById(id);
    }
}
