package org.maximsavin.api_bookstore.domain.genre;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GenreMapper {

    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    GenreDto genreToGenreDto(Genre genre);

    Genre genreRequestToGenre(GenreRequest request);

    void updateGenre(Genre source, @MappingTarget Genre target);
}
