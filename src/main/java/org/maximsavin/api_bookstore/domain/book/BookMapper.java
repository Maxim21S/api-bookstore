package org.maximsavin.api_bookstore.domain.book;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDto bookToBookDto(Book book);

    Book bookRequestToBook(BookRequest request);

    @Mapping(target = "version", ignore = true)
    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "genres", ignore = true)
    void updateBook(Book source, @MappingTarget Book target);
}
