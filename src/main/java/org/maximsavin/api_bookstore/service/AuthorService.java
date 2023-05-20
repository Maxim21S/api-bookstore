package org.maximsavin.api_bookstore.service;

import org.maximsavin.api_bookstore.model.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {

    List<Author> getAll();

    Author getById(long id);

    Author create(Author author);

    Author update(Author author);

    boolean deleteById(long id);
}
