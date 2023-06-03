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
        ResponseEntity<List<GenreDto>> result = underTest.getAll();

        // then
        assertThat(result.getStatusCode()).isEqualTo(expected.getStatusCode());
        assertThat(result.getBody()).isEqualTo(expected.getBody());
        verify(mockedService).getAll();
        verifyNoMoreInteractions(mockedService);
    }

    @Test
    void getById_ExistingId_ShouldReturnGenreDtoResponse() {
        // given
        long id = 12L;
        GenreDto dto = new GenreDto(id, "Fantasy");
        ResponseEntity<GenreDto> expected = ResponseEntity.ok(dto);
        when(mockedService.getById(id)).thenReturn(dto);

        // when
        ResponseEntity<GenreDto> result = underTest.getById(id);

        // then
        assertThat(result.getStatusCode()).isEqualTo(expected.getStatusCode());
        assertThat(result.getBody()).isEqualTo(expected.getBody());
        verify(mockedService).getById(id);
        verifyNoMoreInteractions(mockedService);
    }

    @Test
    @Disabled
    void getById_NonExistingId_() {

    }

    @Test
    void create() {
        // given
        var request = new GenreRequest("Fantasy");
        var response = new GenreDto(12L, "Fantasy");
        when(mockedService.create(request)).thenReturn(response);

        // when
        ResponseEntity<GenreDto> result = underTest.create(request);

        // then
        assertThat(result.getStatusCode()).isEqualTo(result.getStatusCode());
        assertThat(result.getBody()).isEqualTo(result.getBody());
        verify(mockedService).create(request);
        verifyNoMoreInteractions(mockedService);
    }

    @Test
    void update() {
        // given
        long id = 12L;
        var request = new GenreRequest("Horror");
        var response = new GenreDto(id, "Horror");
        ResponseEntity<GenreDto> expected = ResponseEntity.ok(response);
        when(mockedService.update(id, request)).thenReturn(response);

        // when
        ResponseEntity<GenreDto> result = underTest.update(id, request);

        // then
        assertThat(result.getStatusCode()).isEqualTo(expected.getStatusCode());
        assertThat(result.getBody()).isEqualTo(expected.getBody());
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
        long id = 12L;
        ResponseEntity<Object> expected = ResponseEntity.noContent().build();

        // when
        ResponseEntity<String> result = underTest.deleteById(id);

        // then
        assertThat(result.getStatusCode()).isEqualTo(expected.getStatusCode());
        assertThat(result.getBody()).isEqualTo(expected.getBody());
        verify(mockedService).deleteById(id);
        verifyNoMoreInteractions(mockedService);
    }

    @Test
    void deleteById_NonExistingId_ShouldReturnStatus400() {
        // given
        long id = 12L;
        String message = "Genre not found with ID=" + id;
        ResponseEntity<String> expected = ResponseEntity.badRequest()
                .body(message);
        doThrow(new EntityNotFoundException(message))
                .when(mockedService).deleteById(id);

        // when
        ResponseEntity<String> result = underTest.deleteById(id);

        // then
        assertThat(result.getStatusCode()).isEqualTo(expected.getStatusCode());
        assertThat(result.getBody()).isEqualTo(expected.getBody());
        verify(mockedService).deleteById(id);
        verifyNoMoreInteractions(mockedService);
    }


    private static Stream<Arguments> getGenreDtoResponses() {
        return Stream.of(
                Arguments.of(Collections.emptyList()),
                Arguments.of(List.of(
                        new GenreDto(1L, "Fantasy"),
                        new GenreDto(2L, "Romance"))));
    }
}