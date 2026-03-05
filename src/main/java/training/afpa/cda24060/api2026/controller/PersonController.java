package training.afpa.cda24060.api2026.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import training.afpa.cda24060.api2026.dto.LoanDTO;
import training.afpa.cda24060.api2026.dto.PersonDTO;
import training.afpa.cda24060.api2026.dto.filter.PersonFilterDTO;
import training.afpa.cda24060.api2026.exception.BadRequestException;
import training.afpa.cda24060.api2026.exception.ConflictException;
import training.afpa.cda24060.api2026.mapper.PersonMapper;
import training.afpa.cda24060.api2026.model.Person;
import training.afpa.cda24060.api2026.service.PersonService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonMapper personMapper;

    @PostMapping("/person")
    public ResponseEntity<PersonDTO> createPerson(@Valid @RequestBody PersonDTO person) {
        if (personService.existsByFirstnameAndLastname(person.getFirstname(), person.getLastname())) {
            throw new ConflictException("Une personne avec ce prénom et nom existe déjà.");
        }
        Person savedPerson = personMapper.toEntity(person);
        personService.savePerson(savedPerson);
        return new ResponseEntity<>(personMapper.toDTO(savedPerson), HttpStatus.CREATED);
    }

    @GetMapping("/persons")
    public  Page<PersonDTO> getAllPersons(
            @PageableDefault(size = 10 , sort = "lastname") Pageable pageable,
            @ModelAttribute("search") PersonFilterDTO search) {
        return personService.getPersons(pageable, search).map(personMapper::toDTO);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable("id") Integer id) {
        Optional<Person> person = Optional.of(personService.getPerson(id)
                .orElseThrow(() -> new NoSuchElementException("Personne non trouvé avec l'id : " + id + " !")));
        return ResponseEntity.ok(new PersonDTO(person.get()));
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable("id") Integer id, @Valid @RequestBody Person person) {
        // recherche de la personne dans la BDD
        // sinon trouvé exception
        if (!id.equals(person.getId())) {
            throw new BadRequestException("L'ID dans le path et dans le body ne correspondent pas.");
        }
        if (personService.existsByFirstnameAndLastnameAndIdNot(person.getFirstname(), person.getLastname(), id)) {
            throw new ConflictException("Une autre personne avec ce prénom et nom existe déjà.");
        }
        Person personUpdated = personService.getPerson(id)
                .orElseThrow(() -> new NoSuchElementException("Personne non trouvée avec l'ID : " + id));

        // Utilisation de Optional.ofNullable pour éviter les if
        Optional.ofNullable(person.getFirstname()).ifPresent(personUpdated::setFirstname);
        Optional.ofNullable(person.getLastname()).ifPresent(personUpdated::setLastname);

        Person updatedPerson = personService.savePerson(personUpdated);
        return ResponseEntity.ok(new PersonDTO(updatedPerson));
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable("id") Integer id) {
        if (!personService.getPerson(id).isPresent()) {
            throw new NoSuchElementException("Personne non trouvée avec l'ID : " + id);
        }
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    // Nouvelle méthode : Récupération des emprunts d'une personne
    @GetMapping("/person/{id}/loans")
    public List<LoanDTO> getLoansByPerson(@PathVariable("id") Integer id) {
        Person person = personService.getPerson(id)
                .orElseThrow(() -> new NoSuchElementException("Personne non trouvée avec l'ID : " + id));

        return person.getLoans().stream()
                .map(LoanDTO::new)
                .collect(Collectors.toList());
    }
}
