package kea.exercise.persondata_api_exercise.names.dtos;

import java.util.List;

public record FullPersonDto(String fullName, String firstName, String middleName, String lastName, String gender,
                            double genderProbability, int genderCount,
                            int age, int ageCount, List<Country> country, int countryCount) {
}
