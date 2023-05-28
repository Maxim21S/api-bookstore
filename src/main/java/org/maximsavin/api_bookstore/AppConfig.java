package org.maximsavin.api_bookstore;

import org.maximsavin.api_bookstore.domain.genre.GenreCreateRequestToGenreConverter;
import org.maximsavin.api_bookstore.domain.genre.GenreToGenreDtoConverter;
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
        mapper.addConverter(context.getBean(GenreToGenreDtoConverter.class));
        mapper.addConverter(context.getBean(GenreCreateRequestToGenreConverter.class));
        return mapper;
    }
}
