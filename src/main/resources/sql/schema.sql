DROP SCHEMA IF EXISTS bookstore;
CREATE SCHEMA bookstore;

USE bookstore;

CREATE TABLE genres
(
    id      BIGINT AUTO_INCREMENT,
    name    VARCHAR(50) NOT NULL UNIQUE,
    version TIMESTAMP DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP(),
    CONSTRAINT pk_genres PRIMARY KEY (id)
);

CREATE TABLE authors
(
    id      BIGINT AUTO_INCREMENT,
    name    VARCHAR(50) NOT NULL,
    version TIMESTAMP DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP(),
    CONSTRAINT pk_authors PRIMARY KEY (id)
);

CREATE TABLE books
(
    id               BIGINT AUTO_INCREMENT,
    title            VARCHAR(255) NOT NULL,
    description      TEXT,
    isbn             VARCHAR(20)  NOT NULL UNIQUE,
    publication_year DATE,
    version          TIMESTAMP DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP(),
    CONSTRAINT pk_books PRIMARY KEY (id),
    CONSTRAINT chk_description_length CHECK (length(description) < 2000),
    CONSTRAINT chk_isbn_format CHECK (regexp_like(isbn, '^[0-9]{10}$|^[0-9]{13}$'))
);

CREATE INDEX idx_title
    ON books (title);

CREATE TABLE book_author
(
    book_id   BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    CONSTRAINT pk_book_author PRIMARY KEY (book_id, author_id),
    CONSTRAINT fk_book_author_book_id FOREIGN KEY (book_id) REFERENCES books (id) ON DELETE CASCADE,
    CONSTRAINT fk_book_author_author_id FOREIGN KEY (author_id) REFERENCES authors (id) ON DELETE CASCADE
);

CREATE TABLE book_genre
(
    book_id  BIGINT NOT NULL,
    genre_id BIGINT NOT NULL,
    CONSTRAINT pk_book_genre PRIMARY KEY (book_id, genre_id),
    CONSTRAINT fk_book_genre_book_id FOREIGN KEY (book_id) REFERENCES books (id) ON DELETE CASCADE,
    CONSTRAINT fk_book_genre_genre_id FOREIGN KEY (genre_id) REFERENCES genres (id) ON DELETE CASCADE
);