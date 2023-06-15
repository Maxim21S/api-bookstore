package org.maximsavin.api_bookstore.domain.genre;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreDtoMapperImpl implements GenreDtoMapper {

    private final GenreService genreService;
    private final GenreMapper mapper;


    @Override
    public List<GenreDto> getAll() {
        return genreService.getAll().stream()
                .map(mapper::genreToGenreDto)
                .toList();
    }

    @Override
    public GenreDto getById(long id) throws EntityNotFoundException {
        Genre genre = genreService.getById(id);
        return mapper.genreToGenreDto(genre);
    }

    @Override
    public GenreDto create(GenreRequest request) throws DataIntegrityViolationException {
        Genre genre = mapper.genreRequestToGenre(request);
        genre = genreService.create(genre);
        return mapper.genreToGenreDto(genre);
    }

    @Override
    public GenreDto update(long id, GenreRequest request) throws EntityNotFoundException, DataIntegrityViolationException {
        Genre genre = mapper.genreRequestToGenre(request);
        genre.setId(id);
        Genre updated = genreService.update(genre);
        return mapper.genreToGenreDto(updated);
    }

    @Override
    public void deleteById(long id) throws EntityNotFoundException {
        genreService.deleteById(id);
    }
}
