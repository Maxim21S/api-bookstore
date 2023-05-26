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
    private GenreDtoMapperService mockedDtoService;

    @InjectMocks
    private GenreRestController underTest;

    @ParameterizedTest
    @MethodSource("getGenreDtoResponses")
    void getAll_ShouldReturnAllGenreDtoResponses(List<GenreDtoResponse> dtoResponses) {
        // given
        var expected = ResponseEntity.ok(dtoResponses);
        when(mockedDtoService.getAll()).thenReturn(dtoResponses);

        // when
        ResponseEntity<List<GenreDtoResponse>> result = underTest.getAll();

        // then
        assertThat(result.getStatusCode()).isEqualTo(expected.getStatusCode());
        assertThat(result.getBody()).isEqualTo(expected.getBody());
        verify(mockedDtoService).getAll();
        verifyNoMoreInteractions(mockedDtoService);
    }

    @Test
    void getById_ExistingId_ShouldReturnGenreDtoResponse() {
        // given
        long id = 12L;
        GenreDtoResponse dto = new GenreDtoResponse(id, "Fantasy");
        ResponseEntity<GenreDtoResponse> expected = ResponseEntity.ok(dto);
        when(mockedDtoService.getById(id)).thenReturn(dto);

        // when
        ResponseEntity<GenreDtoResponse> result = underTest.getById(id);

        // then
        assertThat(result.getStatusCode()).isEqualTo(expected.getStatusCode());
        assertThat(result.getBody()).isEqualTo(expected.getBody());
        verify(mockedDtoService).getById(id);
        verifyNoMoreInteractions(mockedDtoService);
    }

    @Test
    @Disabled
    void getById_NonExistingId_() {

    }

    @Test
    void create() {
        // given
        var request = new GenreDtoRequest("Fantasy");
        var response = new GenreDtoResponse(12L, "Fantasy");
        when(mockedDtoService.create(request)).thenReturn(response);

        // when
        ResponseEntity<GenreDtoResponse> result = underTest.create(request);

        // then
        assertThat(result.getStatusCode()).isEqualTo(result.getStatusCode());
        assertThat(result.getBody()).isEqualTo(result.getBody());
        verify(mockedDtoService).create(request);
        verifyNoMoreInteractions(mockedDtoService);
    }

    @Test
    void update() {
        // given
        long id = 12L;
        var request = new GenreDtoRequest("Horror");
        var response = new GenreDtoResponse(id, "Horror");
        ResponseEntity<GenreDtoResponse> expected = ResponseEntity.ok(response);
        when(mockedDtoService.update(id, request)).thenReturn(response);

        // when
        ResponseEntity<GenreDtoResponse> result = underTest.update(id, request);

        // then
        assertThat(result.getStatusCode()).isEqualTo(expected.getStatusCode());
        assertThat(result.getBody()).isEqualTo(expected.getBody());
        verify(mockedDtoService).update(id, request);
        verifyNoMoreInteractions(mockedDtoService);
    }

    @Test
    @Disabled
    void update_NonExistingGenre_Should() {

    }

    @Test
    void deleteById() {
        // given
        long id = 12L;
        ResponseEntity<Object> expected = ResponseEntity.noContent().build();

        // when
        ResponseEntity<String> result = underTest.delete(id);

        // then
        assertThat(result.getStatusCode()).isEqualTo(expected.getStatusCode());
        assertThat(result.getBody()).isEqualTo(expected.getBody());
        verify(mockedDtoService).deleteById(id);
        verifyNoMoreInteractions(mockedDtoService);
    }

    @Test
    void deleteById_NonExistingId_Should() {
        // given
        long id = 12L;
        String message = "Genre not found with ID=" + id;
        ResponseEntity<String> expected = ResponseEntity.badRequest()
                .body(message);
        doThrow(new EntityNotFoundException(message))
                .when(mockedDtoService).deleteById(id);

        // when
        ResponseEntity<String> result = underTest.delete(id);

        // then
        assertThat(result.getStatusCode()).isEqualTo(expected.getStatusCode());
        assertThat(result.getBody()).isEqualTo(expected.getBody());
        verify(mockedDtoService).deleteById(id);
        verifyNoMoreInteractions(mockedDtoService);
    }


    private static Stream<Arguments> getGenreDtoResponses() {
        return Stream.of(
                Arguments.of(Collections.emptyList()),
                Arguments.of(List.of(
                        new GenreDtoResponse(1L, "Fantasy"),
                        new GenreDtoResponse(2L, "Romance"))));
    }
}