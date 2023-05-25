package org.maximsavin.api_bookstore.domain.genre;

import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class GenreToGenreDtoResponseConverter
        extends AbstractConverter<Genre, GenreDtoResponse> {

    @Override
    protected GenreDtoResponse convert(Genre source) {
        return new GenreDtoResponse(
                source.getId(),
                source.getName());
    }
}
