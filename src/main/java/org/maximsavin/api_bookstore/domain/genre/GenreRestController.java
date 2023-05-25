package org.maximsavin.api_bookstore.domain.genre;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/genres")
public class GenreRestController {

    private final GenreService genreService;
    private final ModelMapper mapper;

    @Autowired
    public GenreRestController(GenreService genreService, ModelMapper mapper) {
        this.genreService = genreService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<GenreDtoResponse>> getAll() {
        List<GenreDtoResponse> genres = genreService.getAll().stream()
                .map(e -> mapper.map(e, GenreDtoResponse.class))
                .toList();
        return ResponseEntity.ok(genres);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GenreDtoResponse> getById(@PathVariable long id) {
        Genre genre = genreService.getById(id);
        GenreDtoResponse response = mapper.map(genre, GenreDtoResponse.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<GenreDtoResponse> create(@RequestBody GenreDtoRequest request) {
        Genre genre = mapper.map(request, Genre.class);
        genre = genreService.create(genre);
        GenreDtoResponse response = mapper.map(genre, GenreDtoResponse.class);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreDtoResponse> update(
            @PathVariable long id,
            @RequestBody GenreDtoRequest request) {
        Genre genre = genreService.getById(id);
        mapper.map(request, genre);
        genreService.update(genre);
        GenreDtoResponse response = mapper.map(genre, GenreDtoResponse.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        genreService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
