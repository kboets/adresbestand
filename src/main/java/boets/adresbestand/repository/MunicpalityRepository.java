package boets.adresbestand.repository;

import boets.adresbestand.domain.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Asus on 3/02/2017.
 */
public interface MunicpalityRepository extends JpaRepository<Municipality, Long> {

    @Query(value = "select m.city from Municipality m where m.zipCode= ?1")
    List<String> findCitiesByZipCode(Integer zipCode);

    @Query(value = "select m.zipCode from Municipality m where m.city= ?1")
    Integer findZipCodeByCity(String city);


}
