package training.afpa.cda24060.api2026.service;


import io.qameta.allure.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import training.afpa.cda24060.api2026.dto.filter.PersonFilterDTO;
import training.afpa.cda24060.api2026.model.Person;
import training.afpa.cda24060.api2026.repository.PersonRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@Epic("Gestion des personnes")
@Feature("Récupération des personnes")
public class PersonServiceMockAllureTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    @Description("Test de récupération d'une personne par ID")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Récupération d'une personne par son ID")
    @Tag("Mock")
    public void GetPersonByIdTest() {
        Person person = createTestPerson(1, "John", "Doe");

        when(personRepository.findById(1)).thenReturn(Optional.of(person));

        Optional<Person> result = personService.getPerson(1);

        verify(personRepository, times(1)).findById(1);
        assertPersonResult(result, 1, "John", "Doe");
    }

    @Test
    @Description("Test de récupération de toutes les personnes (avec pagination + filtre)")
    @Severity(SeverityLevel.NORMAL)
    @Story("Récupération de toutes les personnes")
    @Tag("Mock")
    public void getPersonsTest() {

        Person person1 = createTestPerson(1, "John", "Doe");
        Person person2 = createTestPerson(2, "Mary", "DoeOuap");

        Page<Person> page = new PageImpl<>(List.of(person1, person2));

        when(personRepository.findAll((Example<Person>) any(), any(Pageable.class)))
                .thenReturn(page);

        Pageable pageable = PageRequest.of(0, 10);
        PersonFilterDTO filter = new PersonFilterDTO(); // vide pour ce test

        Page<Person> result = personService.getPersons(pageable, filter);

        verify(personRepository, times(1))
                .findAll((Example<Person>) any(), any(Pageable.class));

        assertThat(result.getContent()).hasSize(2);
        assertThat(result.getContent().get(0).getFirstname()).isEqualTo("John");
        assertThat(result.getContent().get(1).getFirstname()).isEqualTo("Mary");
    }

    // ===== Helpers =====

    private Person createTestPerson(int id, String firstName, String lastName) {
        Person person = new Person();
        person.setId(id);
        person.setFirstname(firstName);
        person.setLastname(lastName);
        return person;
    }

    private void assertPersonResult(Optional<Person> result, int id, String firstName, String lastName) {
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(id);
        assertThat(result.get().getFirstname()).isEqualTo(firstName);
        assertThat(result.get().getLastname()).isEqualTo(lastName);
    }
}
