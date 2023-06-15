package org.maximsavin.api_bookstore.domain.book;

import java.util.List;

public interface BookDtoMapper {

    List<BookDto> getAll();

    BookDto getById(long id);

    BookDto create(BookRequest request);

    BookDto update(long id, BookRequest request);

    void deleteById(long id);
}
