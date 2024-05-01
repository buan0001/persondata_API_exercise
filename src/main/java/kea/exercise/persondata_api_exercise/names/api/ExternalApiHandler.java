package kea.exercise.persondata_api_exercise.names.api;

import kea.exercise.persondata_api_exercise.names.dtos.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashSet;

@Service
public class ExternalApiHandler {

    private static final String AGE_URL = "https://api.agify.io";
    private static final String GENDER_URL = "https://api.genderize.io";
    private static final String NATIONALITY_URL = "https://api.nationalize.io";
    HashSet<String> names = new HashSet<>();
    HashSet<FullPersonDto> entries = new HashSet<>();

    private final WebClient webClient;

    public ExternalApiHandler() {
        this.webClient = WebClient.create();
    }

    public FullPersonDto getFullPersonData(String name) {
        PersonWithNames tempPerson = new PersonWithNames(name);
        if (names.add(tempPerson.getFullName())) {
            System.out.println("Name added to set");

            // Get the names
            PersonWithNames personWithNames = new PersonWithNames(name);
            // Do API calls, save the responses in DTOs
            PersonAgeDto personAgeResponse = getAgeFromApi(name);
            PersonNationalityDto personNationalityResponse = getNationalityFromApi(name);
            PersonGenderDto personGenderResponse = getGenderFromApi(name);


            FullPersonDto response = new FullPersonDto(personWithNames.getFullName(), personWithNames.getFirstName(), personWithNames.getMiddleName(), personWithNames.getLastName(),
                    personGenderResponse.gender(), personGenderResponse.probability(),
                    personGenderResponse.count(), personAgeResponse.age(), personAgeResponse.count(), personNationalityResponse.country(), personNationalityResponse.count()
            );

            entries.add(response);

            System.out.println(response);
            return response;

        } else {
            System.out.println("Name already in set");
            FullPersonDto found = entries.stream().filter(e -> e.fullName().equals(tempPerson.getFullName())).findFirst().orElse(null);
            System.out.println(found);
            return found;

        }
    }


    public PersonAgeDto getAgeFromApi(String name) {
        //WebClient webClient = WebClient.create();
        PersonAgeDto personAgeResponse = webClient.get()
                .uri(AGE_URL + "?name=" + name)
                .retrieve()
                .bodyToMono(PersonAgeDto.class)
                .block();

        System.out.println(personAgeResponse);

        return personAgeResponse;
    }

    public PersonNationalityDto getNationalityFromApi(String name) {
       // WebClient webClient = WebClient.create();
        PersonNationalityDto personNationalityResponse = webClient.get().uri(NATIONALITY_URL + "?name=" + name)
                .retrieve()
                .bodyToMono(PersonNationalityDto.class)
                .block();

        System.out.println(personNationalityResponse);
        return personNationalityResponse;
    }

    public PersonGenderDto getGenderFromApi(String name) {
       // WebClient webClient = WebClient.create();
        PersonGenderDto personGenderResponse = webClient.get().uri(GENDER_URL + "?name=" + name)
                .retrieve()
                .bodyToMono(PersonGenderDto.class)
                .block();

        System.out.println(personGenderResponse);
        return personGenderResponse;
    }
}
