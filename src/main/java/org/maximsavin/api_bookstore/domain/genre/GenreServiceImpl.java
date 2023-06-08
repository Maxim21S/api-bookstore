package org.maximsavin.api_bookstore.domain.genre;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Genre getById(long id) throws EntityNotFoundException {
        checkIfExists(id);
        return genreRepo.findById(id).get();
    }

    @Override
    public Genre create(Genre newGenre) throws DataIntegrityViolationException {
        if (genreRepo.existsByName(newGenre.getName())) {
            throw new DataIntegrityViolationException(
                    String.format("A genre with the name '%s' already exists.", newGenre.getName()));
        }
        return genreRepo.save(newGenre);
    }

    @Override
    public Genre update(Genre update) throws EntityNotFoundException, DataIntegrityViolationException {
        checkIfExists(update.getId());
        if (genreRepo.existsByName(update.getName())) {
            throw new DataIntegrityViolationException(
                    String.format("A genre with the name '%s' already exists.", update.getName()));
        }
        Genre managed = genreRepo.findById(update.getId()).get();
        mapper.map(update, managed);
        return managed;
    }

    @Override
    public void deleteById(long id) throws EntityNotFoundException {
        checkIfExists(id);
        genreRepo.deleteById(id);
    }


    private void checkIfExists(long id) throws EntityNotFoundException {
        if (!genreRepo.existsById(id))
            throw new EntityNotFoundException("Genre not found with ID=" + id);
    }
}
