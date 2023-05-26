package org.maximsavin.api_bookstore.domain.genre;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class GenreRestController {

    private final GenreDtoMapperService dtoService;


    @GetMapping
    public ResponseEntity<List<GenreDtoResponse>> getAll() {
        List<GenreDtoResponse> response = dtoService.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GenreDtoResponse> getById(@PathVariable long id) {
        GenreDtoResponse response = dtoService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<GenreDtoResponse> create(@RequestBody GenreDtoRequest request) {
        GenreDtoResponse response = dtoService.create(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreDtoResponse> update(
            @PathVariable long id,
            @RequestBody GenreDtoRequest request) {
        GenreDtoResponse response = dtoService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        dtoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
