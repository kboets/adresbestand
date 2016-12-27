package boets.adresbestand.repository;

import boets.adresbestand.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Asus on 22/12/2016.
 */
public interface AddressRepository extends JpaRepository<Address, Long> {


}
