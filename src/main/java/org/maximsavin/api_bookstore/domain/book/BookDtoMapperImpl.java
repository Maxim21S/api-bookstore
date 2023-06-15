package org.maximsavin.api_bookstore.domain.book;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class BookDtoMapperImpl implements BookDtoMapper {

    private final BookService service;
    private final ModelMapper mapper;

    @Override
    public List<BookDto> getAll() {
        List<Book> books = service.getAll();
        return books.stream()
                .map(book -> mapper.map(book, BookDto.class))
                .toList();
    }

    @Override
    public BookDto getById(long id) {
        return mapper.map(service.getById(id), BookDto.class);
    }

    @Override
    public BookDto create(BookRequest request) {
        Book book = mapper.map(request, Book.class);
        book = service.create(book);
        return mapper.map(book, BookDto.class);
    }

    @Override
    public BookDto update(long id, BookRequest request) {
        Book book = mapper.map(request, Book.class);
        book.setId(id);
        book = service.update(book);
        return mapper.map(book, BookDto.class);
    }

    @Override
    public void deleteById(long id) {
        service.deleteById(id);
    }
}
