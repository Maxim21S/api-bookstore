package org.maximsavin.api_bookstore.domain.genre;

import org.modelmapper.AbstractConverter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class GenreDtoRequestToGenreConverter
        extends AbstractConverter<GenreDtoRequest, Genre> {

    @Override
    protected Genre convert(GenreDtoRequest source) {
        return Genre.builder()
                .name(source.name())
                .build();
    }

    @Override
    public Genre convert(MappingContext<GenreDtoRequest, Genre> context) {
        var s = context.getSource();
        var d = context.getDestination();

        if (d == null) d = new Genre();
        d.setName(s.name());

        return d;
    }
}
