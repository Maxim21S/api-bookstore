package org.maximsavin.api_bookstore.domain.author;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/authors")
@RequiredArgsConstructor
public class AuthorRestController {

    private final AuthorDtoMapper service;

    @GetMapping
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody AuthorRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> update(
            @PathVariable long id,
            @RequestBody AuthorRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
