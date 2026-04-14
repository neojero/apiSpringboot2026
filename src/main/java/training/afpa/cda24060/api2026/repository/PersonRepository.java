package training.afpa.cda24060.api2026.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import training.afpa.cda24060.api2026.model.Person;

/*
JpaRepository hérite de :
CrudRepository
PagingAndSortingRepository
*/

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>, JpaSpecificationExecutor<Person> {

    // pas de code
    // en effet, @Repository nous donne acces à un CRUD

    /*
        ces méthodes sont construite par JPA Hibernate selon la signature

        exists by critere == where critere = parametre
    */

    // Vérifie si une personne existe déjà avec ce prénom ET ce nom
    boolean existsByFirstnameAndLastname(String firstname, String lastname);

    // Vérifie si une autre personne (différente de l'ID donné) existe avec ce prénom ET ce nom
    boolean existsByFirstnameAndLastnameAndIdNot(String firstname, String lastname, Integer id);
}
