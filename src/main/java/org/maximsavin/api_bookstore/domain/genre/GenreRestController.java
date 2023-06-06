package org.maximsavin.api_bookstore.domain.genre;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/genres")
@RequiredArgsConstructor
public class GenreRestController {

    private final GenreDtoMapper service;


    @GetMapping
    public ResponseEntity<List<GenreDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable long id) {
        if (id < 0)
            return ResponseEntity.badRequest().body("ID cannot be negative.");//todo DRY
        try {
            return ResponseEntity.ok(service.getById(id));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody GenreRequest request) {
        if (request.name().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(String.format("Invalid genre name '%s'. The name cannot be blank.", request.name()));
        }
        try {
            return ResponseEntity.ok(service.create(request));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreDto> update(
            @PathVariable long id,
            @RequestBody GenreRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable long id) {
        if (id < 0)
            return ResponseEntity.badRequest().body("ID cannot be negative.");//todo DRY
        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(e.getMessage());
        }
    }
}
