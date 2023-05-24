package org.maximsavin.api_bookstore.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.maximsavin.api_bookstore.model.Genre;
import org.maximsavin.api_bookstore.repository.GenreRepo;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatList;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenreServiceImplTests {

    @Mock
    private GenreRepo mockedRepo;

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
        Genre newGenre = Genre.builder().name("Fantasy").build();
        long id = 12L;
        Genre expected = Genre.builder().id(id).name("Fantasy").build();

        // when
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
        verify(mockedRepo).save(newGenre);
        verifyNoMoreInteractions(mockedRepo);
    }

    @Test
    void create_NonNullId_ShouldThrowIllegalArgumentException() {
        // given
        Genre newGenre = Genre.builder().id(12L).name("Fantasy").build();

        // act
        ThrowingCallable act = () -> underTest.create(newGenre);

        // assert
        assertThatThrownBy(act)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("New genre cannot contain ID");

        // verify
        verifyNoInteractions(mockedRepo);
    }

    @Test
    void update_ExistingGenre_ShouldSaveGenreAndReturnSavedGenre() {
        // given
        long id = 12L;
        Genre genre = Genre.builder().id(id).name("Fantasy").build();
        Genre expected = Genre.builder().id(id).name("Fantasy").build();

        // when
        when(mockedRepo.existsById(id)).thenReturn(true);
        when(mockedRepo.save(genre)).thenReturn(new Genre(genre));

        // act
        Genre result = underTest.update(genre);

        // assert
        assertThat(result).isEqualTo(expected);

        // verify
        verify(mockedRepo).existsById(id);
        verify(mockedRepo).save(genre);
        verifyNoMoreInteractions(mockedRepo);
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
