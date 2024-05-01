package kea.exercise.persondata_api_exercise.names;

import kea.exercise.persondata_api_exercise.names.api.ExternalApiHandler;
import kea.exercise.persondata_api_exercise.names.dtos.FullPersonDto;
import org.springframework.stereotype.Service;

@Service
public class NameService {
    private final ExternalApiHandler externalApiHandler;

    public NameService(ExternalApiHandler externalApiHandler) {
        this.externalApiHandler = externalApiHandler;
    }

    public FullPersonDto getNameData(String name) {
        return externalApiHandler.getFullPersonData(name);
    }
}
