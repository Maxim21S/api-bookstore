package org.maximsavin.api_bookstore.domain.genre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GenreRepo extends JpaRepository<Genre, Long> {

    boolean existsByName(String name);

    Genre findByName(String name);

    Set<Genre> findGenresByNameIn(Set<String> names);
}
