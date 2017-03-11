package boets.adresbestand.repository;

import boets.adresbestand.domain.Address;
import boets.adresbestand.domain.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Asus on 22/12/2016.
 */
public interface AddressRepository extends JpaRepository<Address, Long> {

     @Query(value = "select a from Address a where a.street like ?1 and a.houseNumber like ?2 and a.municipality.zipCode = ?3")
    Address findByUniqueConstraint(String street, String houseNumber, Integer zipCode);

    @Query(value = "select a from Address a where a.street like ?1 and a.houseNumber like ?2 and a.box = ?3 and a.municipality.zipCode = ?4")
    Address findByUniqueConstraint(String street, String houseNumber, String box, Integer zipCode);

}
