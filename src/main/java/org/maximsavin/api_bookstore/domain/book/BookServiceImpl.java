package org.maximsavin.api_bookstore.domain.book;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;

    public BookServiceImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }


    @Override
    public List<Book> getAll() {
        return bookRepo.findAll();
    }

    @Override
    public Book getById(long id) {
        return bookRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Book not found with ID=" + id));
    }

    @Override
    public Book create(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public Book update(Book book) {;
        if (!bookRepo.existsById(book.getId()))
            throw new EntityNotFoundException("Book with ID=" + book.getId() + "doesn't exist");
        return bookRepo.save(book);
    }

    @Override
    public void deleteById(long id) {
        getById(id);    // checks if there is an entity with the id
        bookRepo.deleteById(id);
    }
}
