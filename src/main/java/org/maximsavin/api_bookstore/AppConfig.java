package org.maximsavin.api_bookstore;

import org.mapstruct.factory.Mappers;
import org.maximsavin.api_bookstore.domain.author.AuthorMapper;
import org.maximsavin.api_bookstore.domain.book.BookRequestToBookConverter;
import org.maximsavin.api_bookstore.domain.book.BookToBookDtoConverter;
import org.maximsavin.api_bookstore.domain.genre.GenreMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper(ConfigurableApplicationContext context) {
        var mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        mapper.addConverter(context.getBean(BookToBookDtoConverter.class));
        mapper.addConverter(context.getBean(BookRequestToBookConverter.class));
        return mapper;
    }

    @Bean
    public GenreMapper genreMapper() {
        return Mappers.getMapper(GenreMapper.class);
    }

    @Bean
    public AuthorMapper authorMapper() {
        return Mappers.getMapper(AuthorMapper.class);
    }
}
