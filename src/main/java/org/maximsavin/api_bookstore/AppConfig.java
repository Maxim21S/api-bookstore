package org.maximsavin.api_bookstore;

import org.maximsavin.api_bookstore.domain.genre.GenreDtoRequestToGenreConverter;
import org.maximsavin.api_bookstore.domain.genre.GenreToGenreDtoResponseConverter;
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
        mapper.addConverter(context.getBean(GenreToGenreDtoResponseConverter.class));
        mapper.addConverter(context.getBean(GenreDtoRequestToGenreConverter.class));
        return mapper;
    }
}
