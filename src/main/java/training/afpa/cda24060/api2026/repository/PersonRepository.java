package training.afpa.cda24060.api2026.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import training.afpa.cda24060.api2026.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

    // pas de code
    // en effet, @Repository nous donne accés à un CRUD

}
