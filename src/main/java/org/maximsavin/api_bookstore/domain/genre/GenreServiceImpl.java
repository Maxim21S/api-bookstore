package org.maximsavin.api_bookstore.domain.genre;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepo genreRepo;
    private final ModelMapper mapper;


    @Override
    public List<Genre> getAll() {
        return genreRepo.findAll();
    }

    @Override
    public Genre getById(long id) {
        checkIfExists(id);
        Optional<Genre> optional = genreRepo.findById(id);
        return optional.orElse(null);
    }

    @Override
    public Genre create(Genre newGenre) {
        if (newGenre.getId() != null)
            throw new IllegalArgumentException("New genre cannot contain ID");
        return genreRepo.save(newGenre);
    }

    @Override
    public Genre update(Genre update) {
        checkIfExists(update.getId());
        Genre managed = genreRepo.findById(update.getId()).orElse(null);
        mapper.map(update, managed);
        return managed;
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
