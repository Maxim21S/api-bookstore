package org.maximsavin.api_bookstore.domain.author;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
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
