package boets.adresbestand.domain;

import javax.persistence.*;

/**
 * Created by Asus on 26/01/2017.
 */
@Entity
@Table(name = "MUNICIPALITY")
public class Municipality {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ZIPCODE", nullable = false)
    private Integer zipCode;

    @Column(name = "CITY", nullable = false)
    private String city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

