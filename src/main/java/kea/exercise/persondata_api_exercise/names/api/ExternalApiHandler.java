package kea.exercise.persondata_api_exercise.names.api;

import kea.exercise.persondata_api_exercise.names.dtos.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashSet;
import java.util.Objects;

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
        PersonWithNames personWithNames = new PersonWithNames(name);
        String fiN = personWithNames.getFirstName();
        if (names.add(fiN)) {
            System.out.println("Name added to set");


            // Do API calls, save the responses in DTOs
            PersonAgeDto personAgeResponse = getAgeFromApi(fiN);
            PersonNationalityDto personNationalityResponse = getNationalityFromApi(fiN);
            PersonGenderDto personGenderResponse = getGenderFromApi(fiN);


            FullPersonDto response = new FullPersonDto(personWithNames.getFullName(), personWithNames.getFirstName(), personWithNames.getMiddleName(), personWithNames.getLastName(),
                    personGenderResponse.gender(), personGenderResponse.probability(),
                    personGenderResponse.count(), personAgeResponse.age(), personAgeResponse.count(), personNationalityResponse.country(), personNationalityResponse.count()
            );

            entries.add(response);

            System.out.println(response);
            return response;

        } else {
            System.out.println("Name already in set");
            FullPersonDto found = entries.stream().filter(e -> e.fullName().equals(personWithNames.getFullName())).findFirst().orElse(
                    entries.stream().filter(e -> e.firstName().equals(personWithNames.getFirstName())).findFirst().orElse(null)
            );
            if (found == null) {
                throw new RuntimeException("Name not found in set - very strange!!!");
            }
            else if (!Objects.equals(personWithNames.getMiddleName(), found.middleName()) || !Objects.equals(personWithNames.getLastName(), found.lastName())) {
                FullPersonDto entryWithDifferentNameParts = new FullPersonDto(personWithNames, found);
                System.out.println(entryWithDifferentNameParts);
                entries.add(entryWithDifferentNameParts);
                System.out.println("entries after adding new entry: ");

                for (FullPersonDto entry : entries) {
                    System.out.println(entry);
                }
                return entryWithDifferentNameParts;
            }
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
