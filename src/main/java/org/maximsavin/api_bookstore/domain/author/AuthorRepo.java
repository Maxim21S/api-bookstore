package org.maximsavin.api_bookstore.domain.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {

    Author findByName(String name);

    Set<Author> findAuthorsByNameIn(Set<String> names);
}
