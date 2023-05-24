package org.maximsavin.api_bookstore.domain.author;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    Author getById(long id);

    Author create(Author author);

    Author update(Author author);

    void deleteById(long id);
}
