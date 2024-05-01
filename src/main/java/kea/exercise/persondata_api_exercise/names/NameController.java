package kea.exercise.persondata_api_exercise.names;

import kea.exercise.persondata_api_exercise.names.dtos.FullPersonDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class NameController {

    private final NameService nameService;

    public NameController(NameService nameService) {
        this.nameService = nameService;
    }

    @GetMapping("/{name}")
    public FullPersonDto getNameData(@PathVariable String name) {
        return nameService.getNameData(name);
    }
}
