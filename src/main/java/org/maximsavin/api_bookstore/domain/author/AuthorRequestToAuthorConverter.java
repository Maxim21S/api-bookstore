package org.maximsavin.api_bookstore.domain.author;

import org.modelmapper.AbstractConverter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class AuthorRequestToAuthorConverter
        extends AbstractConverter<AuthorRequest, Author> {

    @Override
    protected Author convert(AuthorRequest source) {
        return Author.builder()
                .name(source.name())
                .build();
    }

    @Override
    public Author convert(MappingContext<AuthorRequest, Author> context) {
        var s = context.getSource();
        var d = context.getDestination();

        if (d == null) d = new Author();
        d.setName(s.name());

        return d;
    }
}
