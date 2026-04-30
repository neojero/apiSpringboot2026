package training.afpa.cda24060.api2026.service;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

/**
 * Dans ce test Mockito, nous utilisons @Mock pour créer un mock de PersonRepository
 * et @InjectMocks pour injecter ce mock dans PersonService.
 * Ensuite, nous configurons le comportement du mock pour la méthode getPerson afin qu'elle renvoie
 * une Optional contenant une personne fictive lorsque l'ID spécifié est 1.
 * Ensuite, nous appelons la méthode getPerson de PersonController avec l'ID 1
 * et vérifions que la réponse est correcte.
 */
@ExtendWith(MockitoExtension.class)
public class PersonServiceMockTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    public void GetPersonByIdTest() {
        Person person = new Person();
        person.setId(1);
        person.setFirstname("John");
        person.setLastname("Doe");

        when(personRepository.findById(1)).thenReturn(Optional.of(person));

        Optional<Person> result = personService.getPerson(1);

        verify(personRepository, times(1)).findById(1);
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(1);
        assertThat(result.get().getFirstname()).isEqualTo("John");
        assertThat(result.get().getLastname()).isEqualTo("Doe");
    }

    @Test
    @Description("Test de récupération de toutes les personnes (avec pagination)")
    @Severity(SeverityLevel.NORMAL)
    public void getPersonsTest() {

        Person person1 = new Person();
        person1.setId(1);
        person1.setFirstname("John");
        person1.setLastname("Doe");

        Person person2 = new Person();
        person2.setId(2);
        person2.setFirstname("Mary");
        person2.setLastname("Doe");

        Page<Person> page = new PageImpl<>(Arrays.asList(person1, person2));

        when(personRepository.findAll((Example<Person>) any(), any(Pageable.class)))
                .thenReturn(page);

        Pageable pageable = PageRequest.of(0, 10);
        PersonFilterDTO filter = new PersonFilterDTO();

        Page<Person> result = personService.getPersons(pageable, filter);

        verify(personRepository, times(1))
                .findAll((Example<Person>) any(), any(Pageable.class));

        assertThat(result.getContent()).hasSize(2);
        assertThat(result.getContent().get(0).getFirstname()).isEqualTo("John");
        assertThat(result.getContent().get(1).getFirstname()).isEqualTo("Mary");
    }
}