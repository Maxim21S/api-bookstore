package org.maximsavin.api_bookstore.service;

import org.maximsavin.api_bookstore.model.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> getAll();

    Genre getById(long id);

    Genre create(Genre genre);

    Genre update(Genre genre);

    void deleteById(long id);
}
