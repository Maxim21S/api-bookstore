package org.maximsavin.api_bookstore;

import org.mapstruct.factory.Mappers;
import org.maximsavin.api_bookstore.domain.author.AuthorMapper;
import org.maximsavin.api_bookstore.domain.book.BookMapper;
import org.maximsavin.api_bookstore.domain.genre.GenreMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public GenreMapper genreMapper() {
        return Mappers.getMapper(GenreMapper.class);
    }

    @Bean
    public AuthorMapper authorMapper() {
        return Mappers.getMapper(AuthorMapper.class);
    }

    @Bean
    public BookMapper bookMapper() {
        return Mappers.getMapper(BookMapper.class);
    }
}
