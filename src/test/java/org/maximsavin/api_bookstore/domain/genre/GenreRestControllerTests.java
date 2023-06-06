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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenreRestControllerTests {

    @Mock
    private GenreDtoMapper mockedService;

    @InjectMocks
    private GenreRestController underTest;

    @ParameterizedTest
    @MethodSource("getGenreDtoResponses")
    void getAll_ShouldReturnAllGenreDtoResponses(List<GenreDto> dtoResponses) {
        // given
        var expected = ResponseEntity.ok(dtoResponses);
        when(mockedService.getAll()).thenReturn(dtoResponses);

        // when
        var result = underTest.getAll();

        // then
        assertThat(result.getStatusCode())
                .isEqualTo(expected.getStatusCode());
        assertThat(result.getBody())
                .isEqualTo(expected.getBody());
        verify(mockedService).getAll();
        verifyNoMoreInteractions(mockedService);
    }

    @Test
    void getById_ExistingId_ShouldReturnGenreDtoAndStatus200() {
        // given
        var id = 12L;
        var dto = new GenreDto(id, "Fantasy");
        var expected = ResponseEntity.ok(dto);
        when(mockedService.getById(id)).thenReturn(dto);

        // when
        var result = underTest.getById(id);

        // then
        assertThat(result.getStatusCode())
                .isEqualTo(expected.getStatusCode());
        assertThat(result.getBody())
                .isEqualTo(expected.getBody());
        verify(mockedService).getById(id);
        verifyNoMoreInteractions(mockedService);
    }

    @Test
    void getById_NonExistingId_ShouldReturnStatus404() {
        // given
        var id = 12L;
        var message = "Genre not found with ID=" + id;
        var expected = new ResponseEntity<Object>(message, HttpStatus.NOT_FOUND);
        doThrow(new EntityNotFoundException(message))
                .when(mockedService).getById(id);

        // when
        var result = underTest.getById(id);

        // then
        assertThat(result.getStatusCode())
                .isEqualTo(expected.getStatusCode());
        assertThat(result.getBody())
                .isEqualTo(expected.getBody());
        verify(mockedService).getById(id);
        verifyNoMoreInteractions(mockedService);
    }

    @Test
    void getById_NonValidId_ShouldReturnStatus400() {
        // given
        var id = -12L;
        var message = "ID cannot be negative.";
        var expected = new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);

        // when
        var result = underTest.getById(id);

        // then
        assertThat(result.getStatusCode())
                .isEqualTo(expected.getStatusCode());
        assertThat(result.getBody())
                .isEqualTo(expected.getBody());
        verifyNoInteractions(mockedService);
    }

    @Test
    void create_ValidRequest_ShouldCreateNewGenreAndReturnIt() {
        // given
        var name = "Fantasy";
        var request = new GenreRequest(name);
        var response = new GenreDto(12L, name);
        var expected = ResponseEntity.ok(response);
        when(mockedService.create(request))
                .thenReturn(response);

        // when
        var result = underTest.create(request);

        // then
        assertThat(result.getStatusCode())
                .isEqualTo(expected.getStatusCode());
        assertThat(result.getBody())
                .isEqualTo(expected.getBody());
        verify(mockedService).create(request);
        verifyNoMoreInteractions(mockedService);
    }

    @Test
    void create_ExistingName_ShouldReturnBadRequest() {
        // given
        var name = "Fantasy";
        var message = String.format("A genre with the name '%s' already exists.", name);
        var request = new GenreRequest(name);
        var expected = ResponseEntity.badRequest().body(message);
        doThrow(new DataIntegrityViolationException(message))
                .when(mockedService).create(request);

        // then
        var result = underTest.create(request);

        // when
        assertThat(result.getStatusCode())
                .isEqualTo(expected.getStatusCode());
        assertThat(result.getBody())
                .isEqualTo(expected.getBody());
        verify(mockedService).create(request);
        verifyNoMoreInteractions(mockedService);
    }

    @Test
    void create_InvalidRequest_ShouldReturnBadRequest() {
        // given
        var name = "";
        var request = new GenreRequest(name);
        var message = String.format("Invalid genre name '%s'. The name cannot be blank.", name);
        var expected = ResponseEntity.badRequest().body(message);

        // then
        var result = underTest.create(request);

        // when
        assertThat(result.getStatusCode())
                .isEqualTo(expected.getStatusCode());
        assertThat(result.getBody())
                .isEqualTo(expected.getBody());
        verifyNoInteractions(mockedService);
    }

    @Test
    void update() {
        // given
        var id = 12L;
        var request = new GenreRequest("Horror");
        var response = new GenreDto(id, "Horror");
        var expected = ResponseEntity.ok(response);
        when(mockedService.update(id, request)).thenReturn(response);

        // when
        var result = underTest.update(id, request);

        // then
        assertThat(result.getStatusCode())
                .isEqualTo(expected.getStatusCode());
        assertThat(result.getBody())
                .isEqualTo(expected.getBody());
        verify(mockedService).update(id, request);
        verifyNoMoreInteractions(mockedService);
    }

    @Test
    @Disabled
    void update_NonExistingGenre_Should() {

    }

    @Test
    void deleteById_ExistingId_ShouldDeleteGenreAndReturnStatus204() {
        // given
        var id = 12L;
        var expected = ResponseEntity.noContent().build();

        // when
        var result = underTest.deleteById(id);

        // then
        assertThat(result.getStatusCode())
                .isEqualTo(expected.getStatusCode());
        assertThat(result.getBody())
                .isEqualTo(expected.getBody());
        verify(mockedService).deleteById(id);
        verifyNoMoreInteractions(mockedService);
    }

    @Test
    void deleteById_NonExistingId_ShouldReturnStatus400() {
        // given
        var id = 12L;
        var message = "Genre not found with ID=" + id;
        var expected = ResponseEntity.badRequest().body(message);
        doThrow(new EntityNotFoundException(message))
                .when(mockedService).deleteById(id);

        // when
        var result = underTest.deleteById(id);

        // then
        assertThat(result.getStatusCode())
                .isEqualTo(expected.getStatusCode());
        assertThat(result.getBody())
                .isEqualTo(expected.getBody());
        verify(mockedService).deleteById(id);
        verifyNoMoreInteractions(mockedService);
    }

    @Test
    void deleteById_NonValidId_ShouldReturnStatus400() {
        // given
        var id = -12L;
        var message = "ID cannot be negative.";
        var expected = new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);

        // when
        var result = underTest.deleteById(id);

        // then
        assertThat(result.getStatusCode())
                .isEqualTo(expected.getStatusCode());
        assertThat(result.getBody())
                .isEqualTo(expected.getBody());
        verifyNoInteractions(mockedService);
    }


    private static Stream<Arguments> getGenreDtoResponses() {
        return Stream.of(
                Arguments.of(Collections.emptyList()),
                Arguments.of(List.of(
                        new GenreDto(1L, "Fantasy"),
                        new GenreDto(2L, "Romance"))));
    }
}