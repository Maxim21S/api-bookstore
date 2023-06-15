package org.maximsavin.api_bookstore.domain.author;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDto authorToAuthorDto(Author author);

    Author authorRequestToAuthor(AuthorRequest request);

    void updateAuthor(Author source, @MappingTarget Author target);
}
