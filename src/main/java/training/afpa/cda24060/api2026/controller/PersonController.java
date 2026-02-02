package training.afpa.cda24060.api2026.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;
import training.afpa.cda24060.api2026.model.Person;
import training.afpa.cda24060.api2026.service.PersonService;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/person")
    public Person createPerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }

    @GetMapping("/persons")
    public Iterable<Person> getAllPersons() {
        return personService.getPersons();
    }

    @GetMapping("/person/{id}")
    public Person getPersonById(@PathVariable("id") Integer id) {
        Optional<Person> person = personService.getPerson(id);
        if (!person.isPresent()) {
            throw new NoSuchElementException("Personne non trouvé avec l'id : " + id + " !");
        }
        return person.get();
    }

    @PutMapping("/person/{id}")
    public Person updatePerson(@PathVariable("id") Integer id, @RequestBody Person person) {
        // recherche de la personne dans la BDD
        // sinon trouvé exception
        Person personUpdated = personService.getPerson(id)
                .orElseThrow(() -> new NoSuchElementException("Personne non trouvée avec l'ID : " + id));

        // Utilisation de Optional.ofNullable pour éviter les if
        Optional.ofNullable(person.getFirstname()).ifPresent(personUpdated::setFirstname);
        Optional.ofNullable(person.getLastname()).ifPresent(personUpdated::setLastname);

        return personService.savePerson(personUpdated);
    }

    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable("id") Integer id) {
        personService.deletePerson(id);
    }
}
