package org.maximsavin.api_bookstore.service;

import jakarta.persistence.EntityNotFoundException;
import org.maximsavin.api_bookstore.model.Author;
import org.maximsavin.api_bookstore.repository.AuthorRepo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo authorRepo;

    public AuthorServiceImpl(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }


    @Override
    public List<Author> getAll() {
        return authorRepo.findAll();
    }

    @Override
    public Author getById(long id) {
        return authorRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Author not found with ID=" + id));
    }

    @Override
    public Author create(Author author) {
        return authorRepo.save(author);
    }

    @Override
    public Author update(Author author) {
        if (author.getId() == null)
            throw new IllegalArgumentException("Author's ID cannot be null");
        if (!authorRepo.existsById(author.getId()))
            throw new EntityNotFoundException("Author with ID=" + author.getId() + "doesn't exist");
        return authorRepo.save(author);
    }

    @Override
    public void deleteById(long id) {
        getById(id);    // checks if there is an entity with the id
        authorRepo.deleteById(id);
    }
}