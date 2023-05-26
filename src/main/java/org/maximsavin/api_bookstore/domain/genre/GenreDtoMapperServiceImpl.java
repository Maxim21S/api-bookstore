package org.maximsavin.api_bookstore.domain.genre;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreDtoMapperServiceImpl implements GenreDtoMapperService {

    private final GenreService genreService;
    private final ModelMapper mapper;


    @Override
    public List<GenreDtoResponse> getAll() {
        return genreService.getAll().stream()
                .map(e -> mapper.map(e, GenreDtoResponse.class))
                .toList();
    }

    @Override
    public GenreDtoResponse getById(long id) {
        Genre genre = genreService.getById(id);
        return mapper.map(genre, GenreDtoResponse.class);
    }

    @Override
    public GenreDtoResponse create(GenreDtoRequest request) {
        Genre genre = mapper.map(request, Genre.class);
        genreService.create(genre);
        return mapper.map(genre, GenreDtoResponse.class);
    }

    @Override
    public GenreDtoResponse update(long id, GenreDtoRequest request) {
        Genre genre = mapper.map(request, Genre.class);
        genre.setId(id);
        Genre updated = genreService.update(genre);
        return mapper.map(updated, GenreDtoResponse.class);
    }

    @Override
    public void deleteById(long id) throws EntityNotFoundException {
        genreService.deleteById(id);
    }
}
