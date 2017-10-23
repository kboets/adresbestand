package boets.adresbestand.repository;

import boets.adresbestand.domain.Address;
import boets.adresbestand.domain.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByLastNameContaining(String name);

    List<Person> findByFirstNameContaining(String firstName);

    @Query(value = "select p from Person p where p.lastName LIKE concat('%',?1,'%') and p.firstName LIKE  concat('%',?2,'%')")
    List<Person> searchPerson(String name, String firstName);

    @Query(value = "select a from Address a, Person p where p.lastName LIKE concat('%',?1,'%')")
    List<Address> findMainAddressForName(String lastName);

    @Query(value = "select p from Person p, Address a where p.lastName = :#{#person.lastName} and p.firstName=  :#{#person.firstName} and a.street =  :#{#person.mainAddress.street} and a.municipality.zipCode = :#{#person.mainAddress.municipality.zipCode}")
    Optional<Person> findUniquePerson(@Param("person") Person person);
}
