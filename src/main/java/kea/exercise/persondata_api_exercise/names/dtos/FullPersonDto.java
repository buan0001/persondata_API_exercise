package kea.exercise.persondata_api_exercise.names.dtos;

import java.util.List;

public record FullPersonDto(String fullName, String firstName, String middleName, String lastName, String gender,
                            double genderProbability, int genderCount,
                            int age, int ageCount, List<Country> country, int countryCount) {

    public FullPersonDto (PersonWithNames names, FullPersonDto dto){
        this(names.getFullName(), names.getFirstName(), names.getMiddleName(), names.getLastName(), dto.gender(),
                dto.genderProbability(), dto.genderCount(), dto.age(), dto.ageCount(), dto.country(), dto.countryCount());
    }
}
