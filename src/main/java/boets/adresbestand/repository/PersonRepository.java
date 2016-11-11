package boets.adresbestand.repository;

import boets.adresbestand.domain.Address;
import boets.adresbestand.domain.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Asus on 3/11/2016.
**/
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByLastName(String name);

    @Query(value = "select a from Address a, Person p where p.lastName= ?1")
    List<Address> findMainAddressForName(String lastName);

}
