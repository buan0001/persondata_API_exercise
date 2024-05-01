package kea.exercise.persondata_api_exercise.names.dtos;

import java.util.List;
import java.util.Objects;

public record PersonNationalityDto (String name, List<Country> country, int count) {
}
