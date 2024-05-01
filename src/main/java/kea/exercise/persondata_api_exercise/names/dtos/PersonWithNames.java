package kea.exercise.persondata_api_exercise.names.dtos;

import java.util.Arrays;

public class PersonWithNames {
    private String firstName;
    private String middleName;
    private String lastName;

    public PersonWithNames(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }
    public String getFullName() {

        return getFirstName() + (getMiddleName() == null ? "" : " " + getMiddleName()) + (getLastName() == null ? "" : " " + getLastName());
    }
    public PersonWithNames(String fullName){
        setFullName(fullName);
    }
    public PersonWithNames(){}


    public String setFullName(String fullName) {
        if (fullName == null || fullName.isEmpty()) {
            System.out.println("Name cannot be empty or null");
            return "Name cannot be empty or null";
        }

        String[] names = fullName.split(" ");
        // remove empty strings

        names = Arrays.stream(names).filter(s -> !s.isEmpty()).toArray(String[]::new);
        if (names.length < 1) {
            System.out.println("Name must contain at least 1 part");
            return "Name must contain at least 1 part";
        }

        setFirstName(capitalize( names[0])) ;
        if (names.length > 2) {
            // middleName = names[1];
            StringBuilder correctMiddle = new StringBuilder();

            for (int i = 1; i < names.length-1; i++) {
                correctMiddle.append(names[i]).append(" ");
            }
            setMiddleName( capitalize(correctMiddle.toString().trim()));
            setLastName(capitalize(names[names.length - 1]));
        } else if (names.length > 1) {
            setMiddleName( null);
            setLastName(capitalize(names[1])) ;
        } else {
            setMiddleName(null);
            setLastName(null);
        }
        System.out.println(getMiddleName());
        return getFullName();
    }

    public String capitalize(String name) {
        if (name == null ) {
            return null;
        }
        if (name.isEmpty()) {
            return "";
        }
        int startIndex = name.toLowerCase().contains("mc") ? 2 : 0;
        if (name.contains(" ")){
            int space = name.indexOf(" ");
            if (startIndex == 2){
                return "Mc" + capitalize(name.substring(2, space)) + " " + capitalize(name.substring(space+1));
            }
            return capitalize(name.substring(0, space)) + " " + capitalize(name.substring(space+1));
        }

        if (startIndex == 2 && name.toLowerCase().contains("mc")) {
            return "Mc" + name.substring(2, 3).toUpperCase() + name.substring(3).toLowerCase();
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
