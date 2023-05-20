package org.maximsavin.api_bookstore.service;

import org.maximsavin.api_bookstore.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    List<Book> getAll();

    Book getById(long id);

    Book create(Book book);

    Book update(Book book);

    void deleteById(long id);
}
