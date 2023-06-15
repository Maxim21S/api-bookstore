package org.maximsavin.api_bookstore.domain.book;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.maximsavin.api_bookstore.domain.author.Author;
import org.maximsavin.api_bookstore.domain.author.AuthorRepo;
import org.maximsavin.api_bookstore.domain.genre.Genre;
import org.maximsavin.api_bookstore.domain.genre.GenreRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;
    private final GenreRepo genreRepo;

    private final BookMapper mapper;


    @Override
    public List<Book> getAll() {
        return bookRepo.findAll();
    }

    @Override
    public Book getById(long id) {
        return bookRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Book not found with ID=%d", id)));
    }

    @Override
    public Book create(Book newBook) {
        newBook.setAuthors(
                newBook.getAuthors().stream()
                        .map(Author::getName)
                        .map(authorRepo::findByName)
                        .collect(Collectors.toSet()));

        newBook.setGenres(
                newBook.getGenres().stream()
                        .map(Genre::getName)
                        .map(genreRepo::findByName)
                        .collect(Collectors.toSet()));

        return bookRepo.save(newBook);
    }

    @Override
    public Book update(Book updatedBook) {
        var existingBook = bookRepo.findById(updatedBook.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Book with ID=%d doesn't exist", updatedBook.getId())));

        mapper.updateBook(updatedBook, existingBook);

        existingBook.setAuthors(
                updatedBook.getAuthors().stream()
                        .map(Author::getName)
                        .map(authorRepo::findByName)
                        .collect(Collectors.toSet()));

        existingBook.setGenres(
                updatedBook.getGenres().stream()
                        .map(Genre::getName)
                        .map(genreRepo::findByName)
                        .collect(Collectors.toSet()));

        return existingBook;
    }

    @Override
    public void deleteById(long id) {
        bookRepo.deleteById(id);
    }
}
