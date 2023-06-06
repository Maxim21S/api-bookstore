package org.maximsavin.api_bookstore.domain.genre;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenreServiceImplTests {

    @Mock
    private GenreRepo mockedRepo;

    @Mock
    private ModelMapper mockedMapper;

    @InjectMocks
    private GenreServiceImpl underTest;


    @ParameterizedTest
    @MethodSource("genreListProvider")
    void getAll_ShouldReturnAllGenres(List<Genre> expected) {
        // when
        when(mockedRepo.findAll()).thenReturn(new ArrayList<>(expected));

        // act
        List<Genre> result = underTest.getAll();

        // assert
        assertThatList(result).isEqualTo(expected);

        // verify
        verify(mockedRepo).findAll();
        verifyNoMoreInteractions(mockedRepo);
    }

    @Test
    void getById_ExistingId_ShouldReturnGenre() {
        // given
        long id = 12L;
        Genre expected = Genre.builder().id(id).name("Thriller").build();

        // when
        when(mockedRepo.existsById(id)).thenReturn(true);
        when(mockedRepo.findById(id)).thenReturn(Optional.of(expected));

        // act
        Genre result = underTest.getById(id);

        // assert
        assertThat(result).isEqualTo(expected);

        // verify
        verify(mockedRepo).existsById(id);
        verify(mockedRepo).findById(id);
        verifyNoMoreInteractions(mockedRepo);
    }

    @Test
    void getById_NonExistingId_ShouldThrowEntityNotFoundException() {
        // given
        long id = 12L;

        // when
        when(mockedRepo.existsById(id)).thenReturn(false);

        // act
        ThrowingCallable act = () -> underTest.getById(id);

        // assert
        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(act)
                .withMessage("Genre not found with ID=" + id);

        // verify
        verify(mockedRepo).existsById(id);
        verifyNoMoreInteractions(mockedRepo);
    }

    @Test
    void create_ShouldSaveGenreAndReturnSavedGenre() {
        // given
        var name = "Fantasy";
        Genre newGenre = Genre.builder().name(name).build();
        long id = 12L;
        Genre expected = Genre.builder().id(id).name(name).build();

        // when
        when(mockedRepo.existsByName(name)).thenReturn(false);
        when(mockedRepo.save(newGenre)).then(
                invocation -> {
                    Genre savedGenre = invocation.getArgument(0);
                    savedGenre.setId(id);
                    return savedGenre;
                }
        );

        // act
        Genre result = underTest.create(newGenre);

        // assert
        assertThat(result).isEqualTo(expected);

        // verify
        verify(mockedRepo).existsByName(name);
        verify(mockedRepo).save(newGenre);
        verifyNoMoreInteractions(mockedRepo);
    }

    @Test
    void update_ExistingGenre_ShouldSaveGenreAndReturnSavedGenre() {
        // given
        long id = 12L;
        Genre current = Genre.builder().id(id).name("Manga").build();
        Genre expected = Genre.builder().id(id).name("Fantasy").build();
        Genre update = Genre.builder().id(id).name("Fantasy").build();

        // when
        when(mockedRepo.existsById(id)).thenReturn(true);
        when(mockedRepo.findById(id)).thenReturn(Optional.of(current));

        doAnswer(invocation -> {
            Genre source = invocation.getArgument(0);
            Genre destination = invocation.getArgument(1);
            destination.setName(source.getName());
            return destination;
        }).when(mockedMapper).map(update, current);


        // act
        Genre result = underTest.update(update);

        // assert
        assertThat(result).isEqualTo(expected);

        // verify
        verify(mockedRepo).existsById(id);
        verify(mockedRepo).findById(id);
        verify(mockedMapper).map(update, current);
        verifyNoMoreInteractions(mockedRepo, mockedMapper);
    }

    @Test
    void update_NonExistingGenre_ShouldThrowEntityNotFoundException() {
        // given
        long id = 12L;
        Genre genre = Genre.builder().id(id).name("Fantasy").build();

        // when
        when(mockedRepo.existsById(id)).thenReturn(false);

        // act
        ThrowingCallable act = () -> underTest.update(genre);

        // assert
        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(act)
                .withMessage("Genre not found with ID=" + id);

        // verify
        verify(mockedRepo).existsById(id);
        verifyNoMoreInteractions(mockedRepo);
    }

    @Test
    void deleteById_ExistingId_ShouldDeleteGenre() {
        // given
        long id = 12L;

        // when
        when(mockedRepo.existsById(id)).thenReturn(true);

        // act
        underTest.deleteById(id);

        // verify
        verify(mockedRepo).existsById(id);
        verify(mockedRepo).deleteById(id);
        verifyNoMoreInteractions(mockedRepo);
    }

    @Test
    void deleteById_NonExistingId_ShouldThrowEntityNotFoundException() {
        //given
        long id = 12L;
        when(mockedRepo.existsById(id)).thenReturn(false);

        //act
        ThrowingCallable act = () -> underTest.deleteById(id);

        //assert
        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(act)
                .withMessage("Genre not found with ID=" + id);

        //verify
        verify(mockedRepo, never()).deleteById(any());
    }


    private static Stream<Arguments> genreListProvider() {
        return Stream.of(
                Arguments.of(Collections.emptyList()),
                Arguments.of(List.of(
                        Genre.builder().id(1L).name("Biography").build(),
                        Genre.builder().id(2L).name("Fantasy").build()))
        );
    }
}
