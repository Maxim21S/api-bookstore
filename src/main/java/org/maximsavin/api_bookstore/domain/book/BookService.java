package org.maximsavin.api_bookstore.domain.book;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    Book getById(long id);

    Book create(Book book);

    Book update(Book book);

    void deleteById(long id);
}
