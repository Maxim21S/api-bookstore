package org.maximsavin.api_bookstore.domain.author;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo authorRepo;
    private final AuthorMapper mapper;

    @Override
    public List<Author> getAll() {
        return authorRepo.findAll();
    }

    @Override
    public Author getById(long id) {
        return authorRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Author not found with ID=%d", id)));
    }

    @Override
    public Author create(Author author) {
        return authorRepo.save(author);
    }

    @Override
    public Author update(Author author) {
        var optional = authorRepo.findById(author.getId());

        if (optional.isEmpty())
            throw new EntityNotFoundException(
                    String.format("Author with ID=%d doesn't exist", author.getId()));

        var managed = optional.get();
        mapper.updateAuthor(author, managed);
        return managed;
    }

    @Override
    public void deleteById(long id) {
        var optional = authorRepo.findById(id);

        if (optional.isEmpty())
            throw new EntityNotFoundException(
                    String.format("Author with ID=%d doesn't exist", id));

        authorRepo.deleteById(id);
    }
}
