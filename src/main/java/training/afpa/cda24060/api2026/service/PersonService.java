package training.afpa.cda24060.api2026.service;

import jakarta.persistence.criteria.Predicate;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import training.afpa.cda24060.api2026.dto.filter.PersonFilterDTO;
import training.afpa.cda24060.api2026.model.Person;
import training.afpa.cda24060.api2026.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Optional<Person> getPerson(Integer id) {
        return personRepository.findById(id);
    }

    public Page<Person> getPersons(Pageable pageable, PersonFilterDTO search) {
        return personRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtre sur lastname si non vide
            if (search.getLastname() != null && !search.getLastname().isEmpty()) {
                predicates.add(cb.equal(root.get("lastname"), search.getLastname()));
            }
            // Filtre sur firstname si non vide
            if (search.getFirstname() != null && !search.getFirstname().isEmpty()) {
                predicates.add(cb.equal(root.get("firstname"), search.getFirstname()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable);
    }

    public void deletePerson(Integer id) {
        personRepository.deleteById(id);
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    // Nouvelles méthodes pour vérifier les doublons
    public boolean existsByFirstnameAndLastname(String firstname, String lastname) {
        return personRepository.existsByFirstnameAndLastname(firstname, lastname);
    }

    public boolean existsByFirstnameAndLastnameAndIdNot(String firstname, String lastname, Integer id) {
        return personRepository.existsByFirstnameAndLastnameAndIdNot(firstname, lastname, id);
    }
}
