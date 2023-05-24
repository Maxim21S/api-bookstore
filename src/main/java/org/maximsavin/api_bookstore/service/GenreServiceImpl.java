package org.maximsavin.api_bookstore.service;

import jakarta.persistence.EntityNotFoundException;
import org.maximsavin.api_bookstore.model.Genre;
import org.maximsavin.api_bookstore.repository.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
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
        checkIfExists(id);
        return genreRepo.findById(id).get();
    }

    @Override
    public Genre create(Genre genre) {
        if (genre.getId() != null)
            throw new IllegalArgumentException("New genre cannot contain ID");
        return genreRepo.save(genre);
    }

    @Override
    public Genre update(Genre genre) {
        checkIfExists(genre.getId());
        return genreRepo.save(genre);
    }

    @Override
    public void deleteById(long id) {
        checkIfExists(id);
        genreRepo.deleteById(id);
    }


    private void checkIfExists(long id) {
        if (!genreRepo.existsById(id))
            throw new EntityNotFoundException("Genre not found with ID=" + id);
    }
}
