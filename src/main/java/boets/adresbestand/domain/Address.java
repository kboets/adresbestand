package boets.adresbestand.domain;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ADDRESS", uniqueConstraints = @UniqueConstraint(columnNames = {"STREET", "HOUSENUMBER", "BOX", "MUNICIPALITY_ID"}))
@SequenceGenerator(name = "ADDRESS_SEQ", sequenceName = "ADDRESS_S", allocationSize = 1)
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESS_SEQ")
    private Long id;

    @Column(name = "STREET")
    private String street;

    @Column(name = "HOUSENUMBER")
    private String number;

    @Column(name = "BOX")
    private String box;

    @ManyToOne
    @JoinColumn(name = "MUNICIPALITY_ID", referencedColumnName = "ID")
    private Municipality municipality;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getStreet());
        builder.append(" " +getNumber());
        if(StringUtils.isNotBlank(getBox())){
            builder.append(" " +getBox());
        }
        builder.append(" " +getMunicipality().getZipCode());
        builder.append(" " +getMunicipality().getCity());
        return builder.toString();
    }
}
