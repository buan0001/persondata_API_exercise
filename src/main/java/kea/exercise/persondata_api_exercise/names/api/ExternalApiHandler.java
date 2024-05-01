package kea.exercise.persondata_api_exercise.names.api;

import kea.exercise.persondata_api_exercise.names.dtos.FullPersonResponseDto;
import kea.exercise.persondata_api_exercise.names.dtos.PersonAgeResponseDto;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.HashSet;

public class ExternalApiHandler {

    String AGE_URL = "https://api.agify.io";
    String GENDER_URL = "https://api.genderize.io";
    String NATIONALITY_URL = "https://api.nationalize.io";
    HashSet<String> names = new HashSet<>();
    HashSet<FullPersonResponseDto> entries = new HashSet<>();

    public FullPersonResponseDto getFullPerson(String name) {
        if ( names.add(name)){
            System.out.println("Name added to set");
        } else {
            System.out.println("Name already in set");
        }
        PersonAgeResponseDto personAgeResponse = getPerson(name);

       FullPersonResponseDto response = new FullPersonResponseDto(personAgeResponse.name(), personAgeResponse.age(), personAgeResponse.count());
         entries.add(response);
         for (FullPersonResponseDto entry : entries) {
             System.out.println(entry);
         }
        return response;
    }

    public PersonAgeResponseDto getPerson(String name) {
        WebClient webClient = WebClient.create();
        PersonAgeResponseDto personAgeResponse = webClient.get()
                .uri(AGE_URL + "?name=" + name)
                .retrieve()
                .bodyToMono(PersonAgeResponseDto.class)
                .block();

        System.out.println(personAgeResponse);

        return personAgeResponse;
    }
}
