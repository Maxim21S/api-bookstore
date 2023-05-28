package org.maximsavin.api_bookstore.domain.genre;

import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class GenreToGenreDtoConverter
        extends AbstractConverter<Genre, GenreDto> {

    @Override
    protected GenreDto convert(Genre source) {
        return new GenreDto(
                source.getId(),
                source.getName());
    }
}
