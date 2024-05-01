package kea.exercise.persondata_api_exercise.names.api;

import kea.exercise.persondata_api_exercise.names.dtos.PersonAgeResponseDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExternalApiHandlerTest {

    @Test
    void getPerson() {
        // Arrange
        ExternalApiHandler externalApiHandler = new ExternalApiHandler();

        // Act
        PersonAgeResponseDto response = externalApiHandler.getPerson("John");

        // Assert
        assertNotNull(response);
        assertEquals("John", response.name());
    }
}