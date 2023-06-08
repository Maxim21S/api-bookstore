package org.maximsavin.api_bookstore.domain.author;

import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class AuthorToAuthorDtoConverter
        extends AbstractConverter<Author, AuthorDto> {

    @Override
    protected AuthorDto convert(Author source) {
        return new AuthorDto(
                source.getId(),
                source.getName());
    }
}
