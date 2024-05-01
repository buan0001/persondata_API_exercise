package kea.exercise.persondata_api_exercise.names;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameControllerTest {

    @Test
    void getNames() {
        NameController nameController = new NameController();
        assertEquals("Hello World", nameController.getNames());
    }
}