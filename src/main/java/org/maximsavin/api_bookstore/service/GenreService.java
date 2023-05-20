package org.maximsavin.api_bookstore.service;

import org.maximsavin.api_bookstore.model.Genre;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface GenreService {

    Set<Genre> getAll();

    Genre getById(long id);

    Genre create(Genre genre);

    Genre update(Genre genre);

    boolean deleteById(long id);
}
