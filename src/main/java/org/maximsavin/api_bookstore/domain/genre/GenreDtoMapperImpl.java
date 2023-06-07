package org.maximsavin.api_bookstore.domain.genre;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreDtoMapperImpl implements GenreDtoMapper {

    private final GenreService genreService;
    private final ModelMapper mapper;


    @Override
    public List<GenreDto> getAll() {
        return genreService.getAll().stream()
                .map(e -> mapper.map(e, GenreDto.class))
                .toList();
    }

    @Override
    public GenreDto getById(long id) throws EntityNotFoundException {
        Genre genre = genreService.getById(id);
        return mapper.map(genre, GenreDto.class);
    }

    @Override
    public GenreDto create(GenreRequest request) throws DataIntegrityViolationException {
        Genre genre = mapper.map(request, Genre.class);
        genreService.create(genre);
        return mapper.map(genre, GenreDto.class);
    }

    @Override
    public GenreDto update(long id, GenreRequest request) throws EntityNotFoundException, DataIntegrityViolationException {
        Genre genre = mapper.map(request, Genre.class);
        genre.setId(id);
        Genre updated = genreService.update(genre);
        return mapper.map(updated, GenreDto.class);
    }

    @Override
    public void deleteById(long id) throws EntityNotFoundException {
        genreService.deleteById(id);
    }
}
