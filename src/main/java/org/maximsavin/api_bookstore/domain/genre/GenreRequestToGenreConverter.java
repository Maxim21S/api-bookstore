package org.maximsavin.api_bookstore.domain.genre;

import org.modelmapper.AbstractConverter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class GenreRequestToGenreConverter
        extends AbstractConverter<GenreRequest, Genre> {

    @Override
    protected Genre convert(GenreRequest source) {
        return Genre.builder()
                .name(source.name())
                .build();
    }

    @Override
    public Genre convert(MappingContext<GenreRequest, Genre> context) {
        var s = context.getSource();
        var d = context.getDestination();

        if (d == null) d = new Genre();
        d.setName(s.name());

        return d;
    }
}
