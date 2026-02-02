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
        Optional<Person> personOptional = personService.getPerson(id);
        if (personOptional.isPresent()) {
            Person personToUpdate = personOptional.get();

            String firstName = person.getFirstname();
            if (firstName != null) {
                personToUpdate.setFirstname(firstName);
            }
            String lastName = person.getLastname();
            if (lastName != null) {
                personToUpdate.setLastname(lastName);
            }
            personService.savePerson(personToUpdate);
            return personToUpdate;
        } else {
            return null;
        }
    }

    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable("id") Integer id) {
        personService.deletePerson(id);
    }
}
