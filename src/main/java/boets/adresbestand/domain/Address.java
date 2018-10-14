package boets.adresbestand.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ADDRESS")//, uniqueConstraints = @UniqueConstraint(columnNames = {"STREET", "HOUSENUMBER", "BOX", "MUNICIPALITY_ID"}))
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "STREET")
    private String street;

    @Column(name = "HOUSENUMBER")
    private String houseNumber;

    @Column(name = "BOX")
    private String box;

    @ManyToOne(cascade = javax.persistence.CascadeType.DETACH)
    @JoinColumn(name = "MUNICIPALITY_ID", referencedColumnName = "ID")
    private Municipality municipality;

    @JsonIgnore
    @OneToMany(mappedBy="mainAddress", fetch=FetchType.EAGER)
    private Set<Person> persons;

    public String getValue() {
        return this.toString();
    }

    @Transient
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public Municipality getMunicipality() {
        if(municipality == null){
            municipality = new Municipality();
        }
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    public Set<Person> getPersons() {
        if(persons == null){
            persons = new HashSet<>();
        }
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    public void addPerson(Person person) {
        getPersons().add(person);
        person.setMainAddress(this);
    }



    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getStreet());
        builder.append(" " +getHouseNumber());
        if(StringUtils.isNotBlank(getBox())){
            builder.append(" " +getBox());
        }
        builder.append(" " +getMunicipality().getZipCode());
        builder.append(" " +getMunicipality().getCity());
        return builder.toString();
    }


}
