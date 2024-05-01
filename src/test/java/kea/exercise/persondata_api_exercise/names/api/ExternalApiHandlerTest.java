package kea.exercise.persondata_api_exercise.names.api;

import kea.exercise.persondata_api_exercise.names.dtos.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;

class ExternalApiHandlerTest {


    @Test
    void getAge() {
        // Arrange
        ExternalApiHandler externalApiHandler = new ExternalApiHandler();

        // Act
        PersonAgeDto response = externalApiHandler.getAgeFromApi("John");

        // Assert
        assertNotNull(response);
        assertEquals("John", response.name());
    }

    @Test
    void getFullPerson() {

    }


    @Test
    void getNationalityFromApi() {
        ExternalApiHandler externalApiHandler = new ExternalApiHandler();

        // Act
        PersonNationalityDto response = externalApiHandler.getNationalityFromApi("John");

        // Assert
        assertNotNull(response);
        assertNotNull(response.country());
        assertEquals("John", response.name());
    }

    @Test
    void getGenderFromApi() {
        //ExternalApiHandler externalApiHandler = new ExternalApiHandler(WebClient.Builder);
        ExternalApiHandler externalApiHandler = new ExternalApiHandler();

        PersonGenderDto response = externalApiHandler.getGenderFromApi("John");

        assertNotNull(response);
        assertEquals("John", response.name());
    }

    @Test
    void createNullPerson(){
        PersonWithNames personWithNames = new PersonWithNames();

        assertNull(personWithNames.getFirstName());
        assertNull(personWithNames.getMiddleName());
        assertNull(personWithNames.getLastName());
    }

    @Test
    void testFullApiHandler(){
        ExternalApiHandler externalApiHandler = new ExternalApiHandler();

        FullPersonDto response = externalApiHandler.getFullPersonData("John");


        assertNotNull(response);
        assertEquals("John", response.fullName());

    }

    @Test
    void testFullApiHandlerTwiceWithSameName(){
        ExternalApiHandler externalApiHandler = new ExternalApiHandler();

        FullPersonDto response = externalApiHandler.getFullPersonData("John");
        FullPersonDto response2 = externalApiHandler.getFullPersonData("John");

        assertNotNull(response);
        //assertNotNull(response2);
        assertEquals(response, response2);
    }

    @Test
    void testFullApiHandlerTwiceWithSameFirstNameDifferentLast(){
        ExternalApiHandler externalApiHandler = new ExternalApiHandler();

        FullPersonDto response = externalApiHandler.getFullPersonData("John d");
        FullPersonDto response2 = externalApiHandler.getFullPersonData("John b");

        assertNotNull(response);
        assertNotNull(response2);
        assertNotEquals(response, response2);
        assertEquals(response.firstName(), response2.firstName());
        assertNotEquals(response.lastName(), response2.lastName());
    }

    @Test
    void testFullApiHandlerTwiceWithSameFirstNameDifferentLastAndMiddle(){
        ExternalApiHandler externalApiHandler = new ExternalApiHandler();

        FullPersonDto response = externalApiHandler.getFullPersonData("John a b");
        FullPersonDto response2 = externalApiHandler.getFullPersonData("John c d");

        assertNotNull(response);
        assertNotNull(response2);
        assertNotEquals(response, response2);
        assertEquals(response.firstName(), response2.firstName());
        assertNotEquals(response.lastName(), response2.lastName());
        assertNotEquals(response.middleName(), response2.middleName());
    }



//    @Test
//    void splitTesting(){
//        String fullName = "    John Doe";
//        String[] names = fullName.split(" ");
//        for (int i = 0; i < names.length; i++) {
//            System.out.println(names[i] + i);
//        }
//
//        assertEquals("John", names[0]);
//    }

    @Test
    void splitTestingWithPersonWithNames(){
        PersonWithNames personWithNames = new PersonWithNames();
        String fullName = "    John Doe";
        String[] names = fullName.split(" ");
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i] + i);
        }
        personWithNames.setFullName(fullName);

        assertEquals("John", personWithNames.getFirstName());
        assertEquals("Doe", personWithNames.getLastName());
    }
}