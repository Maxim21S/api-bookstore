INSERT INTO genres (name)
VALUES ('Fiction'),
       ('Mystery'),
       ('Science Fiction'),
       ('Fantasy'),
       ('Romance'),
       ('Thriller'),
       ('Biography'),
       ('History');

INSERT INTO authors (name)
VALUES ('Jane Austen'),
       ('Agatha Christie'),
       ('J.R.R. Tolkien'),
       ('Stephen King'),
       ('William Shakespeare'),
       ('Dan Brown'),
       ('Michelle Obama'),
       ('George R.R. Martin');

INSERT INTO books (title, description, isbn, publication_year)
VALUES ('Pride and Prejudice', 'A classic novel about love and social status.', '9780141439518', '1813-01-28'),
       ('Murder on the Orient Express', 'A detective novel featuring the famous detective Hercule Poirot.',
        '9780062073495', '1934-01-01'),
       ('The Hobbit', 'An adventure story set in a fantasy world.', '9780547928227', '1937-09-21'),
       ('The Shining', 'A horror novel about a family\'s stay at an isolated hotel.', '9780307743657', '1977-01-28'),
       ('Romeo and Juliet', 'A tragic love story set in Verona.', '9780743477116', '1597-01-28'),
       ('The Da Vinci Code', 'A thriller involving a hidden secret of religious significance.', '9780307474278',
        '2003-03-18'),
       ('Becoming', 'The memoir of former First Lady Michelle Obama.', '9781524763138', '2018-11-13'),
       ('A Game of Thrones', 'The first book in the epic fantasy series A Song of Ice and Fire.', '9780553381689',
        '1996-08-01');

INSERT INTO book_author (book_id, author_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 6),
       (7, 7),
       (8, 8);

INSERT INTO book_genre (book_id, genre_id)
VALUES (1, 1),
       (2, 2),
       (2, 6),
       (3, 3),
       (3, 4),
       (4, 1),
       (4, 6),
       (5, 1),
       (5, 5),
       (6, 1),
       (6, 7),
       (7, 7),
       (8, 4);
