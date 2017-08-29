package boets.adresbestand.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "PERSON")
@SequenceGenerator(name = "PERSON_SEQ", sequenceName = "PERSON_S", allocationSize = 1)
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_SEQ")
    private Long id;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "MOBILE_PHONE")
    private String mobilePhone;
    @ElementCollection
    @Column(name = "EMAIL")
    private Set<String> emails;

    @ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")
    private Address mainAddress;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Address getMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(Address mainAddress) {
        this.mainAddress = mainAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Set<String> getEmails() {
        if(emails == null) {
            emails = new HashSet<>();
        }
        return emails;
    }

    public void setEmails(Set<String> emails) {
        this.emails = emails;
    }

    public void addEmail(String email){
        getEmails().add(email);
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Person has name ");
        builder.append(this.getLastName());
        builder.append(" and first name ");
        builder.append(this.getFirstName());
        builder.append(" and address ");
        builder.append(this.getMainAddress().toString());
        return builder.toString();
    }
}
