package org.maximsavin.api_bookstore.service;

import org.maximsavin.api_bookstore.model.Book;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface BookService {
    Set<Book> getAll();

    Book getById(long id);

    Book create(Book book);

    Book update(Book book);

    boolean deleteById(long id);
}
