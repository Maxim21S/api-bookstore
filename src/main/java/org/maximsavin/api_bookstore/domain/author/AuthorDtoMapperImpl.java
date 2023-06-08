package org.maximsavin.api_bookstore.domain.author;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorDtoMapperImpl implements AuthorDtoMapper {

    private final AuthorServiceImpl service;
    private final ModelMapper mapper;

    @Override
    public List<AuthorDto> getAll() {
        return service.getAll().stream()
                .map(e -> mapper.map(e, AuthorDto.class))
                .toList();
    }

    @Override
    public AuthorDto getById(long id) {
        return mapper.map(service.getById(id), AuthorDto.class);
    }

    @Override
    public AuthorDto create(AuthorRequest request) {
        var author = mapper.map(request, Author.class);
        author = service.create(author);
        return mapper.map(author, AuthorDto.class);
    }

    @Override
    public AuthorDto update(long id, AuthorRequest request) {
        var author = mapper.map(request, Author.class);
        author.setId(id);
        author = service.update(author);
        return mapper.map(author, AuthorDto.class);
    }

    @Override
    public void deleteById(long id) {
        service.deleteById(id);
    }
}
