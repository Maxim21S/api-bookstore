package org.maximsavin.api_bookstore.service;

import org.maximsavin.api_bookstore.model.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    Author getById(long id);

    Author create(Author author);

    Author update(Author author);

    void deleteById(long id);
}
