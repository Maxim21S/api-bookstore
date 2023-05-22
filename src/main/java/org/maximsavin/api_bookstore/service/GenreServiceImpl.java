package org.maximsavin.api_bookstore.service;

import jakarta.persistence.EntityNotFoundException;
import org.maximsavin.api_bookstore.model.Genre;
import org.maximsavin.api_bookstore.repository.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class GenreServiceImpl implements GenreService {

    private final GenreRepo genreRepo;

    @Autowired
    public GenreServiceImpl(GenreRepo genreRepo) {
        this.genreRepo = genreRepo;
    }


    @Override
    public List<Genre> getAll() {
        return genreRepo.findAll();
    }

    @Override
    public Genre getById(long id) {
        return genreRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Genre not found with ID=" + id)
        );
    }

    @Override
    public Genre create(Genre genre) {
        if (genre.getId() != null)
            throw new IllegalArgumentException("New genre cannot contain ID");
        return genreRepo.save(genre);
    }

    @Override
    public Genre update(Genre genre) {
        if (!genreRepo.existsById(genre.getId()))
            throw new EntityNotFoundException("Genre with ID=" + genre.getId() + "doesn't exist");
        return genreRepo.save(genre);
    }

    @Override
    public void deleteById(long id) {
        getById(id);    // checks if there is an entity with the id
        genreRepo.deleteById(id);
    }
}
