package kea.exercise.persondata_api_exercise.names;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class NameController {

    @GetMapping
    public String getNames() {
        return "Hello World";
    }
}
