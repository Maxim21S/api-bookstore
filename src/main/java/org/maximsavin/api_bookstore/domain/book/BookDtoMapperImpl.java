package org.maximsavin.api_bookstore.domain.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class BookDtoMapperImpl implements BookDtoMapper {

    private final BookService service;
    private final BookMapper mapper;

    @Override
    public List<BookDto> getAll() {
        List<Book> books = service.getAll();
        return books.stream()
                .map(mapper::bookToBookDto)
                .toList();
    }

    @Override
    public BookDto getById(long id) {
        return mapper.bookToBookDto(service.getById(id));
    }

    @Override
    public BookDto create(BookRequest request) {
        Book book = mapper.bookRequestToBook(request);
        book = service.create(book);
        return mapper.bookToBookDto(book);
    }

    @Override
    public BookDto update(long id, BookRequest request) {
        Book book = mapper.bookRequestToBook(request);
        book.setId(id);
        book = service.update(book);
        return mapper.bookToBookDto(book);
    }

    @Override
    public void deleteById(long id) {
        service.deleteById(id);
    }
}
