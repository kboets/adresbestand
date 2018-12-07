package boets.adresbestand.web.form;


import boets.adresbestand.domain.Person;

import java.util.List;

public class SearchAddressForm {


    private String lastName;

    private String firstName;

    private List<Person> personList;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
